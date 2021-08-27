/**
 * 
 */
package com.devBoard.common.tag;

import java.util.Comparator;

import org.apache.commons.validator.ValidatorAction;

/**
 * @Class Name : ValidatorActionComparator.java
 * @Description : ValidatorActionComparator.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 7. 27.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 7. 27.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class ValidatorActionComparator implements Comparator<ValidatorAction> {
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(ValidatorAction va1, ValidatorAction va2) {
        if((va1.getDepends() == null || va1.getDepends().length() == 0) && (va2.getDepends() == null || va2.getDepends().length() == 0))
            return 0;
        if(va1.getDepends() != null && va1.getDepends().length() > 0 && (va2.getDepends() == null || va2.getDepends().length() == 0))
            return 1;
        if((va1.getDepends() == null || va1.getDepends().length() == 0) && va2.getDepends() != null && va2.getDepends().length() > 0)
            return -1;
        else
            return va1.getDependencyList().size() - va2.getDependencyList().size();
    }
}
