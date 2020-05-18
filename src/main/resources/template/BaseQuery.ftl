package	${queryPackage};

import	lombok.Builder;
import  lombok.Data;
import  lombok.NoArgsConstructor;

import	java.io.Serializable;
import	java.util.List;

/***
 *
 * @author Create By AutoGenerator
 */
@Data
@Builder
@NoArgsConstructor
public class BaseQuery implements Serializable {


	private List<SortMode> sorts;

    private Integer offset;

    private Integer rows;

    private int pageNo=1;

    private int pageSize=10;

}
