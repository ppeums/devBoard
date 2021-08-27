<?xml version="1.0"?>
<!--
   - Copyright (c) 2003.  All Rights Reserved.
   - Candlelight Software
   - Steven P. Punte
   - steve@candlelightsoftware.com
   - http://www.candlelightsoftware.com
   -
   - Use for development and experimentation freely without warranty.
   - For use in production environment contact author.
   -
   -
   - This software is provided 'As Is' and any expressed or implied warranties,
   - including, but not limited to, the implied warranties of merchantability and
   - fitness for a particular  purpose are  disclaimed.  In no event shall
   - CandlelightSoftware be liable for any direct, indirect, incidental, special,
   - exemplary, or consequential damages (including, but not limited to, procurement
   - of substitute goods or services; loss of use, data, or profits; or business
   - interruption) however caused and on any  theory of liability, whether in contract,
   - strict liability, or tort (including  negligence or  otherwise) arising in
   - any way out of the  use of this software, even if advised of the possibility of such
   - damage.
   -->

<!-- DESCRIPTION
   -		A second XSL spreadsheet is used, other some of the report elements
   -		(i.e. <rpt:column>, etc...) would be processed too early in the
   -		report rendering pipeline.  Thus, a second file is used.  There are
   -		probably single file solutions, but this solution seems the simplest.
   -
   -		Translate SQL Query data and report data directives into the primitive
   -		report serializer command set (i.e. <rpt:add>, <rpt:copy>, <rpt:shift>.
   _		See file com.clsw.cocoon.Report.java for processing code.
   -->
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:http="http://xml.apache.org/cocoon/requestgenerator/2.0"
		xmlns:sql="http://apache.org/cocoon/SQL/2.0"
		xmlns:xi="http://www.w3.org/2001/XInclude"
		xmlns:rpt="http://cocoon.clsw.com/report">


	<xsl:param name="label"/>
	<xsl:param name="absolute-path"/>



	<!--  TOP LEVEL RENDER REPORT TEMPLATE  ***************** -->
	<!--  TOP LEVEL RENDER REPORT TEMPLATE  ***************** -->
	<!--  Used at THRID instance of xslt in pipeline -->
	<xsl:template match="/rpt:report-sql">
		<rpt:report-excel>
			<rpt:template-path><xsl:value-of select="$absolute-path"/>/reports/<xsl:value-of select="$label"/>/template.xls</rpt:template-path>
				<xsl:apply-templates select="rpt:data-blocks"/> <!-- =+= |rpt:label|http:requestParameters"/> -->
		</rpt:report-excel>
	</xsl:template>

	<!-- Hack: otherwise an odd construct of "xmlns:xmlns:sql=...." occurs -->
	<xsl:template match="rpt:data-blocks">
		<xsl:apply-templates select="rpt:data-block"/>
	</xsl:template>
	<xsl:template match="rpt:data-block">
		<xsl:apply-templates select="rpt:column|rpt:row|rpt:matrix|rpt:row-copy-shift|rpt:col-copy-shift"/>
	</xsl:template>


	<!-- COLUMN -->
	<!-- COLUMN -->
	<!-- Create column of data
	   - Example:
	   -		<rpt:column>
		 -			<rpt:qry-name>amount</rpt:qry-name>
		 -			<rpt:adrs-column>G</rpt:adrs-column>
		 -			<rpt:adrs-row>7</rpt:adrs-row>
		 -			<rpt:type>java.lang.Double</rpt:type>
		 -		</rpt:column>  -->
	<xsl:template match="rpt:column">

		<xsl:variable name="qry-name"    select="rpt:qry-name"/>
		<xsl:variable name="type-name"   select="rpt:type"/>
		<xsl:variable name="adrs-column" select="translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '01234567890123456789012345') + 10 * translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '00000000001111111111222222')"/>
		<xsl:variable name="adrs-row"    select="rpt:adrs-row - 1"/>

		<!-- If offset attribute exists on element adrs-column, then adjust column -->

		<xsl:for-each select="../sql:rowset/sql:row/*[local-name(.)=$qry-name]">
			<rpt:add>
				<xsl:attribute name="type"><xsl:value-of select="$type-name"/></xsl:attribute>
				<xsl:attribute name="col"><xsl:value-of select="$adrs-column"/></xsl:attribute>
				<xsl:attribute name="row"><xsl:value-of select="number($adrs-row) + position() - 1"/></xsl:attribute>
				<xsl:value-of select="."/>
			</rpt:add>
		</xsl:for-each>

		<xsl:for-each select="../sql:rowset/sql:error">
			<rpt:add>
				<xsl:attribute name="type">java.lang.String</xsl:attribute>
				<xsl:attribute name="col"><xsl:value-of select="$adrs-column"/></xsl:attribute>
				<xsl:attribute name="row"><xsl:value-of select="number($adrs-row) + position() - 1"/></xsl:attribute>
				<xsl:value-of select="."/>
			</rpt:add>
		</xsl:for-each>
	</xsl:template>


	<!-- ROW -->
	<!-- ROW -->
	<!-- Create row of data
	   - Example:
	   -		<rpt:row>
		 -			<rpt:qry-name>amount</rpt:qry-name>
		 -			<rpt:adrs-column>G</rpt:adrs-column>
		 -			<rpt:adrs-row>7</rpt:adrs-row>
		 -			<rpt:type>java.lang.Double</rpt:type>
		 -		</rpt:row>  -->
	<xsl:template match="rpt:row">

		<xsl:variable name="qry-name"    select="rpt:qry-name"/>
		<xsl:variable name="type-name"   select="rpt:type"/>
		<xsl:variable name="adrs-column" select="translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '01234567890123456789012345') + 10 * translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '00000000001111111111222222')"/>
		<xsl:variable name="adrs-row"    select="rpt:adrs-row - 1"/>

		<xsl:for-each select="../sql:rowset/sql:row/*[local-name(.)=$qry-name]">
			<rpt:add>
				<xsl:attribute name="type"><xsl:value-of select="$type-name"/></xsl:attribute>
				<xsl:attribute name="col"><xsl:value-of select="number($adrs-column) + position() - 1"/></xsl:attribute>
				<xsl:attribute name="row"><xsl:value-of select="$adrs-row"/></xsl:attribute>
				<xsl:value-of select="."/>
			</rpt:add>
		</xsl:for-each>

		<xsl:for-each select="../sql:rowset/sql:error">
			<rpt:add>
				<xsl:attribute name="type">java.lang.String</xsl:attribute>
				<xsl:attribute name="col"><xsl:value-of select="number($adrs-column) + position() - 1"/></xsl:attribute>
				<xsl:attribute name="row"><xsl:value-of select="$adrs-row"/></xsl:attribute>
				<xsl:value-of select="."/>
			</rpt:add>
		</xsl:for-each>
	</xsl:template>


	<!-- MATRIX -->
	<!-- MATRIX -->
	<!-- Outer construct is going down, inner construct is going accorss. -->
	<xsl:template match="rpt:matrix">

		<xsl:variable name="qry-name"    select="rpt:qry-name"/>
		<xsl:variable name="type-name" select="rpt:type"/>
		<xsl:variable name="adrs-column" select="translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '01234567890123456789012345') + 10 * translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '00000000001111111111222222')"/>
		<xsl:variable name="adrs-row" select="rpt:adrs-row - 1"/>

		<!-- Double loop -->
		<xsl:for-each select="../sql:rowset/sql:row">
			<xsl:variable name="row-position" select="number($adrs-row) + (position() - 1)"/>
			<xsl:for-each select="sql:rowset/sql:row/*[local-name(.)=$qry-name]">
				<rpt:add>
					<xsl:attribute name="type"><xsl:value-of select="$type-name"/></xsl:attribute>
					<xsl:attribute name="col"><xsl:value-of select="number($adrs-column) + (position() - 1)"/></xsl:attribute>
					<xsl:attribute name="row"><xsl:value-of select="$row-position"/></xsl:attribute>
					<xsl:value-of select="."/>
				</rpt:add>
			</xsl:for-each>
		</xsl:for-each>

	</xsl:template>


	<!-- ROW COPY SHIFT -->
	<!-- ROW COPY SHIFT -->
	<!-- Do two tasks:
				1)	Shift all rows below adrs-row down by the number
						of rows emitted by the database.
				2)	Copy content and formatting of specified portion
						of row to those new rows below it.  Typically formatting
						is what is important.
			 EXAMPLE:
			 	<rpt:row-copy-shift>
					<rpt:adrs-row>6</rpt:adrs-row>
				</rpt:row-copy-shift>
	-->
	<xsl:template match="rpt:row-copy-shift">

		<xsl:variable name="adrs-row" select="rpt:adrs-row - 1"/>
		<xsl:variable name="adrs-col" select="translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '01234567890123456789012345') + 10 * translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '00000000001111111111222222')"/>

		<!-- Command report serializer to shift down content -->
		<rpt:shift-down>
			<xsl:attribute name="row"><xsl:value-of select="rpt:adrs-row"/></xsl:attribute>
			<xsl:attribute name="offset"><xsl:value-of select="count(../sql:rowset/sql:row) - 1"/></xsl:attribute>
			<xsl:attribute name="edge"><xsl:value-of select="$adrs-col"/></xsl:attribute>
		</rpt:shift-down>

		<!-- Loop over number of rows emitted by database -->
		<xsl:for-each select="../sql:rowset/sql:row">
			<xsl:if test="position() &gt; 1">
				<rpt:copy-row>
					<xsl:attribute name="source"><xsl:value-of select="$adrs-row"/></xsl:attribute>
					<xsl:attribute name="dest"><xsl:value-of select="number($adrs-row) + position() - 1"/></xsl:attribute>
					<xsl:attribute name="edge"><xsl:value-of select="$adrs-col"/></xsl:attribute>
				</rpt:copy-row>
			</xsl:if>
		</xsl:for-each>

	</xsl:template>



	<!-- COLUMN COPY SHIFT -->
	<!-- COLUMN COPY SHIFT -->
	<!-- Do two tasks:
				1)	Shift all columns right of adrs-col right by the number
						of rows emitted by the database.
				2)	Copy content and formatting of specified portion
						of column to those new columns.  Typically formatting
						is what is important.
			 EXAMPLE:
			<rpt:col-copy-shift>
				<rpt:adrs-column>C</rpt:adrs-column>
				<rpt:adrs-row>5</rpt:adrs-row>
			</rpt:col-copy-shift>
	-->
	<xsl:template match="rpt:col-copy-shift">

		<xsl:variable name="adrs-col" select="translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '01234567890123456789012345') + 10 * translate( rpt:adrs-column, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '00000000001111111111222222')"/>
		<xsl:variable name="adrs-row" select="rpt:adrs-row - 1"/>

		<!-- Command report serializer to shift down content -->
		<rpt:shift-right>
			<xsl:attribute name="col"><xsl:value-of select="$adrs-col + 1"/></xsl:attribute>
			<xsl:attribute name="offset"><xsl:value-of select="count(../sql:rowset/sql:row) - 1"/></xsl:attribute>
			<xsl:attribute name="edge"><xsl:value-of select="$adrs-row"/></xsl:attribute>
		</rpt:shift-right>

		<!-- Loop over number of rows emitted by database -->
		<xsl:for-each select="../sql:rowset/sql:row">
			<xsl:if test="position() &gt; 1">
				<rpt:copy-col> <!-- <copy-col source="3" dest="7"/> copys all elements from col 3 to col 7 -->
					<xsl:attribute name="source"><xsl:value-of select="$adrs-col"/></xsl:attribute>
					<xsl:attribute name="dest"><xsl:value-of select="number($adrs-col) + position() - 1"/></xsl:attribute>
					<xsl:attribute name="edge"><xsl:value-of select="$adrs-row"/></xsl:attribute>
				</rpt:copy-col>
			</xsl:if>
		</xsl:for-each>

	</xsl:template>

	<!-- COPY -->
	<!-- COPY -->
	<!--
	<xsl:template match="node()">
   <xsl:copy>
      <xsl:copy-of select="@*" />
      <xsl:apply-templates select="node()" />
   </xsl:copy>
	</xsl:template>
-->

</xsl:stylesheet>