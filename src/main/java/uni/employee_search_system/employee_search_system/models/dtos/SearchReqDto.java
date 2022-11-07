package uni.employee_search_system.employee_search_system.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchReqDto {

	private String searchCondition;
	private String searchConditionAdditionalInfo;
	private boolean name;
	private boolean ssn;
	private boolean bdate;
	private boolean address;
	private boolean sex;
	private boolean salary;
	private boolean supervisor;
	private boolean department;


}
