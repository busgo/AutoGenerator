package	${queryPackage};

import	lombok.Data;

import	java.io.Serializable;
import	java.util.List;

/***
 *
 * @author Create By AutoGenerator
 */
@Data
public class BaseQuery implements Serializable {


	private List<SortMode> sorts;

    private Integer offset;

    private Integer rows;

}
