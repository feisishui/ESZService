package com.sinohealth.eszservice.service.news.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinohealth.eszorm.entity.news.NewsColsEntity;
import com.sinohealth.eszservice.dao.news.INewscolsDao;
import com.sinohealth.eszservice.dao.news.IVersionDao;
import com.sinohealth.eszservice.dto.news.NewsColsDto;
import com.sinohealth.eszservice.service.news.INewsColsService;

@Service("newscolsService")
public class NewscolsServiceImpl implements INewsColsService {

	@Autowired
	private INewscolsDao newscolsDao;

	@Autowired
	private IVersionDao versionDao;

	@Override
	public NewsColsDto getNewsCols(Integer ver, String appName) {
		NewsColsDto dto = new NewsColsDto();
		if ("".equals(String.valueOf(ver))) {
			dto.setErrCode(NewsColsDto.ERRCODE_NEED_INFO);
			dto.setErrMsg("输入信息不完整.");
			return dto;
		}
		String newscols = "newscols";
		Integer dbVer = versionDao.findByVer(newscols).getVerValue();
		System.out.println("older version:"+dbVer);
		// 数据库中的版本号大于输入的版本号才查询 
		if (dbVer > ver) {
			List<NewsColsEntity> newsCols = newscolsDao.findNewsByValidInt(1,
					appName);
			dto.setNewsCol(dbVer, newsCols);
			return dto;
		} else {
			dto.setErrCode(NewsColsDto.ERRCODE_NOT_CHANGE);
			dto.setErrMsg("版本无变化，无需更新.");
			return dto;
		}
	}

}
