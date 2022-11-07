package uni.employee_search_system.employee_search_system.commons.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my-db")
@Getter @Setter
public class DBProperty {

	private String url;
	private String username;
	private String password;

}
