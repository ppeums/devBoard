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
   -   	First and second XSL Transformer instances in main cocoon
   -		report generating pipeline.  First usage retrives data insertion
   -		directives from associated "report scheme" file.  Second usage
   -		prepares SQL Query statements with input parameters.
   -->
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:http="http://xml.apache.org/cocoon/requestgenerator/2.0"
		xmlns:sql="http://apache.org/cocoon/SQL/2.0"
		xmlns:xi="http://www.w3.org/2001/XInclude"
		xmlns:rpt="http://cocoon.clsw.com/report">

	<xsl:param name="label"/>



	<!--  CREATE XINCLUDE QUERY STATEMENTS  ***************** -->
	<!--  CREATE XINCLUDE QUERY STATEMENTS  ***************** -->
	<!--  Used at FIRST instance of xslt in the render logic pipeline -->
	<xsl:template match="http:request">
		<rpt:report-xinclude>
			<xsl:apply-templates select="http:requestParameters"/>
			<xi:include>
				<xsl:attribute name="href">reports/<xsl:value-of select="$label"/>/scheme.xml#xpointer(/rpt:report/rpt:data-blocks)</xsl:attribute>
			</xi:include>
		</rpt:report-xinclude>
	</xsl:template>




	<!--  TOP LEVEL RENDER REPORT TEMPLATE  ***************** -->
	<!--  TOP LEVEL RENDER REPORT TEMPLATE  ***************** -->
	<!--  Used at SECOND instance of xslt in the render logic pipeline -->
	<xsl:template match="/rpt:report-xinclude">
		<rpt:report-sql>
			<xsl:apply-templates select="rpt:data-blocks"/> <!-- =+= |rpt:label|http:requestParameters"/> -->
		</rpt:report-sql>
	</xsl:template>


	<!--  RETRIVE AND INSERT REQUEST PARAMETERS  ***************** -->
	<!--  RETRIVE AND INSERT REQUEST PARAMETERS  ***************** -->
	<xsl:template match="rpt:get-select|get-text">
		<xsl:variable name="label" select="."/>
		<xsl:value-of select="/rpt:report-xinclude/http:requestParameters/http:parameter[@name=$label]/http:value"/>
	</xsl:template>


	<!--  RETRIVE AND INSERT REQUEST PARAMETERS  ***************** -->
	<!--  RETRIVE AND INSERT REQUEST PARAMETERS  ***************** -->
	<xsl:template match="rpt:get-date">
		<xsl:variable name="label-day"><xsl:value-of select="."/>-dd</xsl:variable>
		<xsl:variable name="label-month"><xsl:value-of select="."/>-mm</xsl:variable>
		<xsl:variable name="label-year"><xsl:value-of select="."/>-yy</xsl:variable>
		<xsl:value-of select="/rpt:report-xinclude/http:requestParameters/http:parameter[@name=$label-year]/http:value"/>-<xsl:text/>
		<xsl:value-of select="/rpt:report-xinclude/http:requestParameters/http:parameter[@name=$label-month]/http:value"/>-<xsl:text/>
		<xsl:value-of select="/rpt:report-xinclude/http:requestParameters/http:parameter[@name=$label-day]/http:value"/>
	</xsl:template>


	<!-- COPY -->
	<!-- COPY -->
	<xsl:template match="node()">
   <xsl:copy>
      <xsl:copy-of select="@*" />
      <xsl:apply-templates select="node()" />
   </xsl:copy>
	</xsl:template>


</xsl:stylesheet>