package	${queryPackage};

import    lombok.AllArgsConstructor;
import    lombok.Builder;
import    lombok.Data;
import    lombok.NoArgsConstructor;

import	java.io.Serializable;
/***
 *
 * @author Create By AutoGenerator
 */
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
public class SortMode implements Serializable {


	private String columnName;

	private SortModeEnum sortMode;


	public SortMode(String columnName, SortModeEnum sortMode) {
		this.columnName = columnName;
		this.sortMode = sortMode;
	}
}
