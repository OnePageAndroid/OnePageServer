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
		return defualImage(location, dayType);
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

	private LocationImageDto defualImage(Location location, DayType dayType){
		if("MORNING".equals(dayType.toString()))
			return LocationImageDto.of(location,"http://res.cloudinary.com/one-person/image/upload/v1487822238/gzhuxr1h6ltewrclzzt1.png",dayType);
		return LocationImageDto.of(location,"http://res.cloudinary.com/one-person/image/upload/v1487822271/xhy0ckphqer7oyccqguk.png",dayType);
	}
}

