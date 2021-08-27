/*******************************************************************************
 * Copyright (c) 2003.  All Rights Reserved.
 * Candlelight Software
 * Steven P. Punte
 * steve@candlelightsoftware.com
 * http://www.candlelightsoftware.com
 *
 * See legal terms on bottom of this file.
 *
 * Description:
 *
 * Most of the hard work of creating the binary ms-excel report is
 * performed in class Report.  This class simply fits clsss Report
 * into the Cocoon framework and call the other class.
 * Notes and Future Enhancements:
 *
 * Options:
 *******************************************************************************/
package com.clsw.cocoon;

import org.apache.cocoon.serialization.AbstractSerializer;
import java.io.OutputStream;


public class ReportSerializer extends AbstractSerializer {

    /**
      * The primary report object
      */
    Report report;


    /**
      * The current <code>mime-type</code>.
      */
    protected String mimetype = "application/vnd.ms-excel";


    /**
     * Should we set the content length ?
     */
    protected boolean setContentLength = true;

    /**
     * Return the MIME type.
     */
    public String getMimeType() {
        return mimetype;
    }

    /**
     *
     */
    public void setOutputStream(OutputStream out) {
    	report = new Report();
    	report.setOutputStream( out);
    	setContentHandler( report);
    }


    /**
      * Recycle serializer by removing references
      */
    public void recycle() {
        super.recycle();
        report = null;
    }

    /**
    * Test if the component wants to set the content length
    */
    public boolean shouldSetContentLength() {
        return this.setContentLength;
    }
}

/*******************************************************************************
 * Copyright (c) 2003.  All Rights Reserved.
 * Candlelight Software
 * Steven P. Punte
 * steve@candlelightsoftware.com
 * http://www.candlelightsoftware.com
 *
 * Use for development and experimentation freely without warranty.
 * For use in production environment contact author.
 *
 * This software is provided 'As Is' and any expressed or implied warranties,
 * including, but not limited to, the implied warranties of merchantability and
 * fitness for a particular  purpose are  disclaimed.  In no event shall
 * CandlelightSoftware be liable for any direct, indirect, incidental, special,
 * exemplary, or consequential damages (including, but not limited to, procurement
 * of substitute goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any  theory of liability, whether in contract,
 * strict liability, or tort (including  negligence or  otherwise) arising in
 * any way out of the  use of this software, even if advised of the possibility of such
 * damage.
 */