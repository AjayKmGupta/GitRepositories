package in.channelplay.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import in.channelplay.entity.HTMLParserReport;
import in.channelplay.enums.HTMLParserEnums;
import in.channelplay.service.HTMLParserService;

@Service
public class HTMLParserServiceImpl implements HTMLParserService {

	@Override
	public HTMLParserReport getHTMLParserReport(String htmlText) {
		HTMLParserReport report = new HTMLParserReport(1L, getCurrentFormattedDateTime(), HTMLParserEnums.INVALID,
				null);
		boolean isValid = isValidHTML(htmlText);
		if (isValid)
			report.setStatus(HTMLParserEnums.VALID);
		return report;
	}

	private boolean isValidHTML(String htmlText) {
		final String HTML_TAG_FORMAT_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
		Pattern pattern = Pattern.compile(HTML_TAG_FORMAT_PATTERN);
		Matcher matcher = pattern.matcher(htmlText);
		return matcher.matches();

	}

	private String getCurrentFormattedDateTime() {

		Calendar calendar = GregorianCalendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm z");
		df.setCalendar(calendar);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date = df.format(calendar.getTime());
		return date;
	}

}
