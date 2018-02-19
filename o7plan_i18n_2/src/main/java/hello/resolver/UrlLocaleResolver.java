package hello.resolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.LocaleResolver;
/***
 * @author AnhTu
 * Class này chỉ override hàm resolveLocale().
 * hàm này return 1 đối tượng kiểu java.util.Locale dựa vào
 * đường dẫn URL
 */
public class UrlLocaleResolver implements LocaleResolver {

	private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";
	private static final String LANGUAGE_NAME = "LANGUAGE_NAME";

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// ==> /o7plan_i18n/en/...
		// ==> /o7plan_i18n/ja/...
		// ==> /o7plan_i18n/WEB-INF/pages/...
		String uri = request.getRequestURI();

		System.out.println("URI=" + uri);

		String prefixEn = request.getServletContext().getContextPath() + "/en/";
		String prefixJp = request.getServletContext().getContextPath() + "/ja/";
		String prefixVi = request.getServletContext().getContextPath() + "/vi/";

		Locale locale = null;
		String languageName = "";
		HttpSession session = request.getSession();

		// English
		if (uri.startsWith(prefixEn)) {
			locale = Locale.ENGLISH;
			languageName = "en";
		}
		// Japanese
		else if (uri.startsWith(prefixJp)) {
			//locale = Locale.JAPAN;
			locale = new Locale("ja", "JP");
			languageName = "ja";
		}
		// Vietnamese
		else if (uri.startsWith(prefixVi)) {
			locale = new Locale("vi", "VN");
			languageName = "vi";
		}
		
		if (locale != null) {
			session.setAttribute(URL_LOCALE_ATTRIBUTE_NAME, locale);
		} else {
			locale = (Locale) session.getAttribute(URL_LOCALE_ATTRIBUTE_NAME);
			if (locale == null) {
				locale = Locale.ENGLISH;
			}
			languageName = (String) session.getAttribute(LANGUAGE_NAME);
			if("".equals(languageName)) {
				languageName = "en";
			}
		}

		session.setAttribute(LANGUAGE_NAME, languageName);
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// Nothing
	}

}