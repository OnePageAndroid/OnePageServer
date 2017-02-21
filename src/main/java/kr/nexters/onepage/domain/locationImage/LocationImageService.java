package kr.nexters.onepage.domain.locationImage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.location.LocationService;
import kr.nexters.onepage.domain.util.DaumAPI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationImageService {
	@Autowired
	private LocationImageRepository locationImageRepository;
	@Autowired
	private LocationService locationService;

	@Autowired
	private Cloudinary cloudinary;

	@Transactional(readOnly = true)
	public LocationImageDto findByLocationIdAndDay(Long locationId, DayType dayType) {
		Location location = locationService.findById(locationId);
		LocationImage locationImage = locationImageRepository.findByLocationIdAndDayType(locationId, dayType);
		LocationImageDto locationImageDto = LocationImageDto.of(locationImage, location);
		if (locationImage != null) {
			return locationImageDto;
		}
		return DaumAPI.getImageUrl(location, dayType);
	}

	@Transactional (readOnly = false)
	public void locationImageSave(Long locationId, MultipartFile multipartFile, String name, String englishName, DayType dayType){
		Location location = locationService.findById(locationId);
		Map<String, String> uploadInfo = upload(multipartFile);
		locationImageRepository.save(LocationImage.of(location, uploadInfo, dayType, name, englishName));
	}

	private Map<String, String> upload(MultipartFile multipartFile) {
		try {
			Map<String, String> uploadInfo = cloudinary.uploader().upload(multipartFile.getBytes(), new HashMap() {
				{
					put("resource_type", "auto");
				}
			});
			uploadInfo.put("name", multipartFile.getName());
			return uploadInfo;
		} catch (IOException e) {
			log.error("saveLocationImage : " + e.getMessage());
			throw new OnePageServiceException(e);
		}
	}
}

