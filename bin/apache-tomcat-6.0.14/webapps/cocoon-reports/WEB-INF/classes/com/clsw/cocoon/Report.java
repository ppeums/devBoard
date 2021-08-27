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
 *	Merge incomming data specified by XML SAX stream into
 *  an existing binary MS-Excel document and send out
 *  as binary document (actually java character stream).
 *
 * Notes and Future Enhancements:
 *	o		Add support for multiple sheets.
 *	o		The movement feature <shift-down> has a defect. It presently
 *      does NOT examine attribute "edge".  When code is enabled, a
 *      certain Excel bug is stimulate with "rich summary" example.
 *      I belive the problem is that any row in Excel must have at
 *      least one non-null cell.  This problem is possibly stimulate
 *      in all four copy and movement tags, and needs to be revisited.
 *******************************************************************************/
package com.clsw.cocoon;

import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;

import java.sql.*;

/**
 *	Class Report
 *
 *	Accept an ms-excel document in binary form, accept a set of
 *  data insert commands (specified below) , and then generate
 *  a combined ms-excel binary document.  Use Apache POI libraries
 *  to manipulate ms-excel file.
 *
 *  EXAMPLE:
 *  <rpt:report-excel xmlns:rpt="http://cocoon.clsw.com/report">
 *
 *		<rpt:template-path>C:/wave/src/cocoon-reports/reports/rich/template.xls</rpt:template-path>
 *
 *			<shift-down  row="6" offset="9"/>
 *			<shift-right col="3" offset="4" edge="4"/>
 *
 *			<copy-row source="5" dest="6"/>
 * 			<copy-col source="2" dest="3" edge="4"/>
 *
 *			<rpt:add type="java.lang.String" col="2" row="4">cynthia</rpt:add>
 *
 *  </rpt:report-excel>
 *
 *  SPECIFIC TAG DEFINITION
 *  <rtp:report-excel>
 *    Top level root element.
 *
 *  <rpt:add
 *					type="{java.xxxx.xxx}"
 *					col="{Base 0 Excel Column Address}"
 *					row="{Base 0 Excel Row Address}"
 *			>data</rpt:add>
 *
 *    Insert "data" into ms excel spread sheet at specified row and column
 *    location.  Optional "type" attributed can: java.lang.String,
 *    java.lang.Double, java.util.Date, or java.lang.Boolean.  Default
 *    is java.lang.String.  The "type" will influence how MS EXCEL
 *    interprets and operates on the cell.  For example, when declared
 *    a double, then numeric formatting is permitted (i.e. digits
 *    after decimal point).
 *
 *  <rpt:shift-down row="5" offset="12">
 *			Shift down everything from row 5 down 12 rows.
 *
 *  <rpt:shift-right col="4" offset="7" edge="2">
 *			Shift right everything from column 4 over 7 columns, but don't
 *      modify anything in rows 0 through 2.
 *
 *  <rpt:copy-row source="3" dest="7">
 *			Copy all cells in row 3 to row 7.
 *
 *  <rpt:copy-column source="3" dest="7" edge="2">
 *			Copy all cells from column 3 to column 7, but don't copy
 *      any cells in rows 0 through 2.
 *
 */
public class Report implements ContentHandler {


  /** The states we are allowed to be in **/
  public static final int STATE_START              = 0;
  public static final int STATE_TEMPLATE_ELEMENT   = 1;
  public static final int STATE_ADD_ELEMENT        = 2;
  public static final int STATE_SHIFTDOWN_ELEMENT  = 3;
  public static final int STATE_COPYROW_ELEMENT    = 4;
  public static final int STATE_SHIFTRIGHT_ELEMENT = 5;
  public static final int STATE_COPYCOL_ELEMENT    = 6;

	/** Write report binary output to this stream */
	protected OutputStream outputStream;

	/** Absolute path to report template file */
	protected String templatePath;

	/** ExtenXLS object representing MS Excel Document */
	protected HSSFWorkbook book;

	/** Keeps track of which node we are processing */
	protected int state = STATE_START;

	/** String, Integer, Float, etc... of node to add to spreadsheet */
	protected String cellType;

	/** Address locations for set tag */
	protected String rowPosition;
	protected String colPosition;

	/** The actual value to be inserted into the excel document */
	protected String cellValue;

	/** Used for copy-row command */
	protected String sourceRow;
	protected String targetRow;
	protected String edgeCol;

	/** Used for shift-down command */
	protected String rowOffset;

	/** Used for copy-col command */
	protected String sourceCol;
	protected String targetCol;
	protected String edgeRow;

	/** Used for shift-right command */
	protected String colOffset;

	/** */
	public boolean developmentFlag = false;


	/** */
	void setOutputStream( OutputStream _out) {
		outputStream = _out;
	}


	/** Receive notification of the beginning of a document */
	public void startDocument() {
		state                = STATE_START;
		cellType             = new String();
		cellValue            = new String();
		templatePath         = new String();
	}


	// Receive notification of the beginning of an element.
	public void startElement(String namespaceURI, String localName, String qName, org.xml.sax.Attributes atts) {
		//debug("StartElement : " + namespaceURI + " : " + localName + " : " + qName + " : " + atts);

		// Safty check
		if( localName == null) return;

		else if( localName.equals( "template-path" ) )
			state = STATE_TEMPLATE_ELEMENT;

		else if( localName.equals( "add" ) ) {
			state = STATE_ADD_ELEMENT;
			cellType = atts.getValue( "type");
			rowPosition = atts.getValue( "row");
			colPosition = atts.getValue( "col");
		}

		else if( localName.equals( "shift-down" ) ) {
			state = STATE_SHIFTDOWN_ELEMENT;
			rowPosition = atts.getValue( "row");
			rowOffset   = atts.getValue( "offset");
			edgeCol     = atts.getValue( "edge");
		}

		else if( localName.equals( "copy-row" ) ) {
			state = STATE_COPYROW_ELEMENT;
			sourceRow = atts.getValue("source");
			targetRow = atts.getValue("dest");
			edgeCol     = atts.getValue( "edge");
		}

		else if( localName.equals( "shift-right" ) ) {
			state = STATE_SHIFTRIGHT_ELEMENT;
			colPosition = atts.getValue( "col");
			colOffset   = atts.getValue( "offset");
			edgeRow     = atts.getValue( "edge");
		}

		else if( localName.equals( "copy-col" ) ) {
			state = STATE_COPYCOL_ELEMENT;
			sourceCol = atts.getValue("source");
			targetCol = atts.getValue("dest");
			edgeRow   = atts.getValue( "edge");
		}
	}


	/**
	 * Receive notification of character data.
	 * Typically we gather up string framents in case there are multiple.
	 */
	public void characters(char[] ch, int start, int length) {

		if( state == STATE_TEMPLATE_ELEMENT)
			templatePath += new String( ch, start, length);

		else if( state == STATE_ADD_ELEMENT)
			cellValue += new String( ch, start, length);
	}


	/**
	 * Receive notification of the end of an element.
	 * The lions-share of software logic is found in this routine.
	 * That is, a case section exists for each element tag that is processed.
	 */
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

		// Safty check
		if( localName == null) return;

		// Safty check
    // Throw sax exception to halt further processing
    else if( book == null && ! localName.equals( "template-path" ) )
			throw new SAXException( "Error: Report.endElement(): HSSF Workbook was null when tag '" + localName + "' was received.");

		// Process element <rpt:template-path>
		else if( localName.equals( "template-path" ) ) {

			if( state != STATE_TEMPLATE_ELEMENT)
				debug( "Error: was NOT expecting ending tag <template-path>");
			else {
				state = STATE_START;
				try {
					File infile = new File( templatePath);
					if( infile == null )
						throw new SAXException( "Error: Report.endElement(): Attempt to open file at '" + templatePath + "' failed");
					FileInputStream fis = new FileInputStream( infile);

					POIFSFileSystem fs = new POIFSFileSystem(fis);
    			book = new HSSFWorkbook(fs);

    			// Throw sax exception to halt further processing
    			if( book == null)
						throw new SAXException( "Error: Report.endElement(): Attempt to create HSSF Workbook from '" + templatePath + "' failed");

				}
				catch (java.io.IOException e) {
					throw new SAXException( "Error: Report.endElement(): Attempt to open file at '" + templatePath + "' failed");
					//debug("endElement() " + e);
				}
			}
		}  // end "template-path"


		// Process element <rpt:add>
		else if( localName.equals( "add" ) ) {
			if( state != STATE_ADD_ELEMENT)
				debug( "Error: was NOT expecting ending tag <add>");

			else if( cellValue == null || cellValue.equals("") )
				; // Do nothing, cell value was empty for some reason

			else {
				try {
					state = STATE_START;

					// Obtain handle to sheets
					HSSFSheet sheet = book.getSheetAt(0);

					// Obtain
					short row = Short.parseShort( rowPosition);
					short col = Short.parseShort( colPosition);

					HSSFRow targetRow = sheet.getRow( row) != null ? sheet.getRow( row) : sheet.createRow( row);
					HSSFCell targetCell = targetRow.getCell( col) != null ?  targetRow.getCell( col) : targetRow.createCell( col);

					if( cellType == null || cellType.equals("") || cellType.equals( "java.lang.String"))
						targetCell.setCellValue( cellValue );

					else if( cellType.equals( "java.lang.Double") ) {
						double doubleVal = Double.parseDouble( cellValue);
						targetCell.setCellValue( doubleVal );
					}

					else if( cellType.equals( "java.util.Date") ) {
						java.util.Date dateVal = java.sql.Date.valueOf( cellValue);
						targetCell.setCellValue( dateVal );
					}

					else if( cellType.equals( "java.lang.Boolean") ) {
						boolean boolVal = java.lang.Boolean.getBoolean( cellValue);
						targetCell.setCellValue( boolVal );
					}

					else
						debug( "Error: <add> : no provisions for type '" + cellType + "'" );

				}
				catch ( java.lang.NumberFormatException e) {
					debug("ERROR endElement() <add>: Cannot cast content \"" + cellValue + "\" into format \"" + cellType + "\".  Check <report>.xml file");
				}
				catch ( java.lang.Exception e) {
					e.printStackTrace();
					debug("ERROR endElement() <add> " + e);
				}
				finally {
					cellValue = new String(); // Clear out for next usage
				}
			}
		} // end <add>


		// Process element <rpt:shift-down>
		else if( localName.equals( "shift-down" ) ) {
			if( state != STATE_SHIFTDOWN_ELEMENT)
				debug( "Error: was NOT expecting ending tag <shift-down>");

			else {
				try {
					state = STATE_START;

					// Obtain handle to sheets
					HSSFSheet sheet = book.getSheetAt(0);

					// Get short values
					short row    = Short.parseShort( rowPosition);
					short offset = Short.parseShort( rowOffset);
					short edge   = Short.parseShort( edgeCol);
					if( offset < 1 ) return;

					for( short r = (short)sheet.getLastRowNum(); r >= row; r-- ) {

						short source = r;
						short target = (short)(r + offset);

						HSSFRow hssfSourceRow = sheet.getRow( source);
						if( hssfSourceRow == null) continue;  // Don't bother if nothing in row

						HSSFRow hssfTargetRow = sheet.createRow( target);

						// Loop over any cells with content
						for( java.util.Iterator iter = hssfSourceRow.cellIterator(); iter.hasNext(); ) {
							HSSFCell sourceCell = (HSSFCell)iter.next();
							short column = sourceCell.getCellNum();
							// =+= PROBLEM: Line below causes corrupt excel files, but no java exceptions.
							// =+= Will need Andy O. assistance to understand what bug is.  Probably some
							//       kind of data inconsistancy is being allowed by POI but not accepted by excel.
							// =+= Andy O. says's excel must have at least one cell in each row.
							// if( column <= edge ) continue;

//debug( "Moving cell col=" + column + " from row=" + source + " to row=" + target + " content=" + cellToString( sourceCell) );

							HSSFCell targetCell = hssfTargetRow.createCell( column);
							targetCell.setCellType( sourceCell.getCellType() );

							if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING)
								targetCell.setCellValue( sourceCell.getStringCellValue() );
							else if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
								targetCell.setCellValue( sourceCell.getNumericCellValue() );
							else if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
								;
							else {
								debug( "ERROR: <shift-down> : Cell col=" + column + " row=" + r + " type=" + sourceCell.getCellType() + " not yet supported.");
								continue;
							}
							targetCell.setCellStyle( sourceCell.getCellStyle());
						}

						if( hssfSourceRow != null) // probably always true
							sheet.removeRow( hssfSourceRow);
					}
				}
				catch ( java.lang.NumberFormatException e) {
					debug("ERROR: <shift-down> : Cannot cast");
					//e.printStackTrace();
				}
				catch ( java.lang.Exception e) {
					//e.printStackTrace();
					debug("endElement():<shift-down> " + e);
				}
			}
		} // end <shift-down>


		// Process element <rpt:copy-row>
		else if( localName.equals( "copy-row" ) ) {
			if( state != STATE_COPYROW_ELEMENT)
				debug( "Error: was NOT expecting ending tag <copy-row>");

			else {
				try {
					state = STATE_START;

					// Obtain handle to sheets
					HSSFSheet sheet = book.getSheetAt(0);

					// Obtain args
					short source = Short.parseShort( sourceRow);
					short target = Short.parseShort( targetRow);
					short edge   = Short.parseShort( edgeCol);

					// Obtain source row
					HSSFRow hssfSourceRow = sheet.getRow( source);
					if( hssfSourceRow == null) return;

					// Create new row
					HSSFRow hssfTargetRow = sheet.createRow( target);

					// Loop over any cells with content
					for( java.util.Iterator iter = hssfSourceRow.cellIterator(); iter.hasNext(); ) {
						HSSFCell sourceCell = (HSSFCell)iter.next();
						short column = sourceCell.getCellNum();
						if( column <= edge ) continue;
//debug( "Copying cell col=" + column + " from row=" + source + " to row=" + target + " content=" + cellToString( sourceCell) );

						HSSFCell targetCell = hssfTargetRow.createCell( column);
						targetCell.setCellType( sourceCell.getCellType() );

						if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING)
							targetCell.setCellValue( sourceCell.getStringCellValue() );
						else if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
							targetCell.setCellValue( sourceCell.getNumericCellValue() );
						else if( sourceCell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
							;
						else {
							debug( "ERROR: <copy-row> : Cell col=" + column + " row=" + source + " type=" + sourceCell.getCellType() + " not yet supported.");
							continue;
						}
						targetCell.setCellStyle( sourceCell.getCellStyle());
					}
				}
				catch ( java.lang.Exception e) {
					e.printStackTrace();
					debug("endElement():<copy-row> source =" + sourceRow + " target=" + targetRow + ": " + e);
				}
			}
		} // end <copy-row>


		// Process element <rpt:shift-right>
		else if( localName.equals( "shift-right" ) ) {
			if( state != STATE_SHIFTRIGHT_ELEMENT)
				debug( "Error: was NOT expecting ending tag 'shift-right'");

			else {
				try {
					state = STATE_START;

					// Obtain handle to sheets
					HSSFSheet sheet = book.getSheetAt(0);

					// Get short values
					short col    = Short.parseShort( colPosition);
					short offset = Short.parseShort( colOffset);
					short edge  = Short.parseShort( edgeRow);
					if( offset < 1 ) return;

					// Loop from edge row down to last row in sheet
					for( int myrow = edge; myrow <= sheet.getLastRowNum(); myrow++ ) {

						// Obtain reference to source row
						HSSFRow hssfSourceRow = sheet.getRow( myrow);
						if( hssfSourceRow == null) continue;

						// Loop over cells in row from most right-most to original column
						for( short mycol = hssfSourceRow.getLastCellNum();  mycol >= col; mycol--) {

							HSSFCell hssfSourceCell = hssfSourceRow.getCell( mycol);
							if( hssfSourceCell == null) continue;


							// Create new cell at target location
							int target = mycol + offset;
							HSSFCell hssfTargetCell = hssfSourceRow.createCell( (short)target);

							//debug( "Moving cell from row=" + myrow + " column=" + mycol +
							//				" to column=" + target + " data=" + cellToString( hssfSourceCell));

  						if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING)
  							hssfTargetCell.setCellValue( hssfSourceCell.getStringCellValue() );
  						else if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
  							hssfTargetCell.setCellValue( hssfSourceCell.getNumericCellValue() );
  						else if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
  							;
  						else {
  							debug( "ERROR: <shift-right> : Cell col=" + mycol + " row=" + myrow + " type=" + hssfSourceCell.getCellType() + " not yet supported.");
  							continue;
  						}
  						hssfTargetCell.setCellStyle( hssfSourceCell.getCellStyle());

  						// Remove source cell
  						hssfSourceRow.removeCell( hssfSourceCell);

						}
					}
				}
				catch ( java.lang.NumberFormatException e) {
					debug("ERROR: <shift-right> : Cannot cast");
					//e.printStackTrace();
				}
				catch ( java.lang.Exception e) {
					//e.printStackTrace();
					debug("endElement():<shift-right> " + e);
				}
			}
		} // end <shift-right>


		// Process element <rpt:copy-col>
		else if( localName.equals( "copy-col" ) ) {
			if( state != STATE_COPYCOL_ELEMENT)
				debug( "Error: was NOT expecting ending tag 'copy-col'");

			else {
				try {
					state = STATE_START;

					// Obtain handle to sheets
					HSSFSheet sheet = book.getSheetAt(0);

					// Obtain args
					short source = Short.parseShort( sourceCol);
					short target = Short.parseShort( targetCol);
					short edge  = Short.parseShort( edgeRow);

					// Loop from edge row down to first null row =+= (short)sheet.getLastRowNum();
					for( int myrow = edge; true; myrow++ ) {

						// Obtain reference to source row
						HSSFRow hssfSourceRow = sheet.getRow( myrow);
						if( hssfSourceRow == null) break; // Loop end when expression is true

						// Obtain reference to souce cell
						HSSFCell hssfSourceCell = hssfSourceRow.getCell( source);
						if( hssfSourceCell == null) continue;

						// Create new cell at target location
						HSSFCell hssfTargetCell = hssfSourceRow.createCell( target);

						if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_STRING)
							hssfTargetCell.setCellValue( hssfSourceCell.getStringCellValue() );
						else if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
							hssfTargetCell.setCellValue( hssfSourceCell.getNumericCellValue() );
						else if( hssfSourceCell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
							;
						else {
							debug( "ERROR: <copy-col> : Cell col=" + source + " row=" + myrow + " type=" + hssfSourceCell.getCellType() + " not yet supported.");
							continue;
						}
						hssfTargetCell.setCellStyle( hssfSourceCell.getCellStyle());
					}
				}
				catch ( java.lang.Exception e) {
					e.printStackTrace();
					debug("endElement():<copy-row> source =" + sourceRow + " target=" + targetRow + ": " + e);
				}
			}
		} // end <copy-col>
	}


	/**
	 * Receive notification of the end of a document.
	 * Write out document to binary stream.
	 */
	public void endDocument() {

		try {
			// Write out
			book.write( outputStream);
			outputStream.close();
		}
		catch (java.io.IOException e) {
			debug("endDocument() " + e);
		}
	}

	/* These member functions are all required by interface ContentHandler but not presently used */

	// End the scope of a prefix-URI mapping.
	public void endPrefixMapping(String prefix) { }

	// Receive notification of ignorable whitespace in element content.
	public void ignorableWhitespace(char[] ch, int start, int length) { }

	// Receive notification of a processing instruction.
	public void processingInstruction(String target, String data) { }

	// Receive an object for locating the origin of SAX document events.
	public void setDocumentLocator(org.xml.sax.Locator locator) { }

	// Receive notification of a skipped entity.
	public void skippedEntity(String name) { }

	// Begin the scope of a prefix-URI Namespace mapping.
	public void startPrefixMapping(String prefix, String uri) { }


	// Use for development and debugging
	protected String cellToString( HSSFCell cell) {

		if( cell.getCellType() == HSSFCell.CELL_TYPE_STRING )
			return( cell.getStringCellValue());

		else if( cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC )
			return ( Double.toString( cell.getNumericCellValue() ));

		else if( cell.getCellType() == HSSFCell.CELL_TYPE_BLANK )
			return ("BLANK");

		else
			return ("CELL TYPE UNKNOWN");
	}
	protected void debug( String str) {
		System.out.println( "Report: " + str);
	}

	/*
	 * For test purposes
	 *
	 * java -Dorg.xml.sax.driver=org.apache.xerces.parsers.SAXParser com.clsw.cocoon.Report rich.xml
	 */
	public static void main(String[] args){

		if( args.length < 1 ) {
			System.out.println( "Error");
			System.out.println( "  Expected Usage java com.clsw.cocoon.Report <reportname>");
			System.exit(1);
		}

		String reportname = args[0];

		try {
			File infile = new File( reportname);
			FileInputStream fis = new FileInputStream( infile);

			File outfile = new File( "result.xls");
			FileOutputStream fos = new FileOutputStream(outfile);

			// Create report object
			Report report = new Report();
			report.developmentFlag = true;

			// Attach output stream
			report.setOutputStream( fos);

			// Create Sax Xml Reader and attach to report
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler( report);

			// This should execute
	    xmlReader.parse(new InputSource(fis));

			// Close down
			fos.close();
			fis.close();
		}
		catch (java.io.IOException e) {
			System.out.println("main() " + e);
			e.printStackTrace();
		}
		catch (org.xml.sax.SAXException e) {
			System.out.println("main() " + e);
			e.printStackTrace();
		}
	}

	/*
	public static void main2(String[] args){

		java.util.Random random = new java.util.Random();

		try {
			Class.forName( "org.gjt.mm.mysql.Driver" ).newInstance();
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/test", "", "");
			Statement  stmt         = con.createStatement();

			for( int i = 0; i < 1000; i++) {

				double amount = 10.0 * random.nextDouble();

				java.sql.Date date = java.sql.Date.valueOf( "2003-01-01");
				long offset = (long)( (31 + 28) * 24.0 * 60.0 * 60.0 * 1000.0 * random.nextDouble());
				date = new java.sql.Date( date.getTime() + offset );

				int n = random.nextInt( 5);
				String staff =
					n == 0 ? "george" :
						n == 1 ? "mel" :
							n == 2 ? "jasson" :
								n == 3 ? "cynthia" : "kathy";


				n = random.nextInt( 5);
				String type =
					n == 0 ? "produce" :
						n == 1 ? "cannedfood" :
							n == 2 ? "diary" :
								n == 3 ? "bread" : "hardware";


    				stmt.executeUpdate( "INSERT INTO transactions SET " + "staff  = '" +
 				staff + "', " + "type   = '" + type + "', " + "date   = '" + date + "',
                   				" + "amount = " + amount ); } } catch( Exception e) {
                                       				System.out.println( e ); } } */
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