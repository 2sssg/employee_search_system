package uni.employee_search_system.employee_search_system.services;

import java.util.Map.Entry;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class CommonService {

	public void convertRedirectModel(Model model, RedirectAttributes redirectAttributes) {

		for(Entry<String, Object> en : model.asMap().entrySet()) {
			redirectAttributes.addFlashAttribute(en.getKey(), en.getValue());
		}
	}
}
