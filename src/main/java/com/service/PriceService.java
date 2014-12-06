package com.service;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PriceDao;
import com.entity.Price;
import com.utils.HttpTookit;
@Transactional
@Service
public class PriceService {

	private static final Logger logger = Logger.getLogger(PriceService.class);
	@Autowired
	private PriceDao priceDao;

	public PriceDao getPriceDao() {
		return priceDao;
	}

	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Scheduled(fixedDelay = 500000)
	/**
	 * 读取网页上的比特币价格
	 */
	public void getPriceFromNet() {
		Price p=new Price();

		//logger.info("开始向BTCWTO取当前比特币价格" + new Date().getTime());
		String html = HttpTookit.doGet("https://vip.btcchina.com/", null, "utf-8",
				true);

		Document doc = Jsoup.parse(html);
		Element price = doc.getElementById("last");
		Element maxprice = doc.getElementById("high");
		Element minprice = doc.getElementById("low");

		if (null == price) {
			logger.error("Price is null...");
			return;
		}		

		try {
			p.setPrice(price.html());
			p.setMaxprice(maxprice.html());
			p.setMinprice(minprice.html());
		} catch (NumberFormatException ex) {
			logger.error(ex);
		}
		this.priceDao.addPrice(p);
		//logger.info("向BTCWTO取当前比特币价格结束" + new Date().getTime());
		//logger.info("当前比特币价格：" + result);
	}
	public Price getPrice() {
		 return this.priceDao.getPrice();
		
	}
}
