package uni.employee_search_system.employee_search_system.models.dtos.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString
public class DeleteReqDto {

	private boolean delete;
	private String ssn;
}
