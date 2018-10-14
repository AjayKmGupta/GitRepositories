package in.channelplay.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.channelplay.entity.HTMLParserReport;
import in.channelplay.service.HTMLParserService;

@RequestMapping("parser")
@RestController
@SessionAttributes("list")
public class HTMLParserController {

	@Autowired
	private HTMLParserService service;

	@RequestMapping(value = "firstparse", method = RequestMethod.POST)
	public String getFirstTimeResponse(@RequestBody String htmlText, Model model) {
		String response = null;
		Date date1 = getCurrentTime();
		List<HTMLParserReport> reportList = new ArrayList<>();
		HTMLParserReport entity = service.getHTMLParserReport(htmlText);
		Date date2 = getCurrentTime();
		Long diff = ((date2.getTime() - date1.getTime()) / 1000) % 60;
		entity.setTimeTaken(String.valueOf(diff) + " SECONDS");
		reportList.add(entity);
		model.addAttribute("list", reportList);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(reportList);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "parse", method = RequestMethod.POST)
	public String getResponse(@RequestBody String htmlText, Model model,
			@ModelAttribute("list") List<HTMLParserReport> reportList) {
		String response = null;
		Date date1 = getCurrentTime();
		HTMLParserReport entity = service.getHTMLParserReport(htmlText);
		Date date2 = getCurrentTime();
		Long diff = ((date2.getTime() - date1.getTime()) / 1000) % 60;
		entity.setTimeTaken(String.valueOf(diff) + " SECONDS");
		entity.setAttemptNum(reportList.get(reportList.size() - 1).getAttemptNum() + 1);
		reportList.add(entity);
		model.addAttribute("list", reportList);
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.writeValueAsString(reportList);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	private Date getCurrentTime() {
		Calendar calendar = GregorianCalendar.getInstance();
		return calendar.getTime();
	}

}
