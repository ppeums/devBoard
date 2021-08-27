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
   -	Translates "Report Schema" document <rpt:parameter> section into
   -	a reasonable HTML page with input submission technology.
   -->
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:http="http://xml.apache.org/cocoon/requestgenerator/2.0"
		xmlns:xi="http://www.w3.org/2001/XInclude"
		xmlns:rpt="http://cocoon.clsw.com/report">


	<xsl:param name="label"/>


	<!--  CREATE HTML PAGE FOR PARAMETER INPUT ***************** -->
	<!--  CREATE HTML PAGE FOR PARAMETER INPUT ***************** -->
	<xsl:template match="rpt:report">
		<html>
			<body>
				<h1>Awesome Report Generation On Apache Cocoon</h1>
				<p><b>Input parameters to report are specified on this page.</b></p>
  			<p><b>Report Description:</b><xsl:value-of select="rpt:description"/></p>
				<xsl:for-each select="rpt:parameters">
					<form method="POST">
						<xsl:attribute name="action"><xsl:value-of select="$label"/>.xls</xsl:attribute>
						<xsl:apply-templates/>
					<p><input type="submit" value="Render Report"/></p>
					</form>
    		</xsl:for-each>
   	</body>
   </html>
	</xsl:template>


	<!--  CREATE HTML REPORT TEXT INPUT  ***************** -->
	<!--  CREATE HTML REPORT TEXT INPUT  ***************** -->
	<!--  This is appropriate for arbitrary text input -->
	<xsl:template match="rpt:input-text">
		<p>
			<xsl:value-of select="rpt:title"/> :
			<input type="text">
				<xsl:attribute name="name"><xsl:value-of select="rpt:label"/></xsl:attribute>
				<xsl:attribute name="value"><xsl:value-of select="rpt:init"/></xsl:attribute>
			</input>
		</p>
	</xsl:template>


	<!--  CREATE HTML REPORT SELECT INPUT  ***************** -->
	<!--  CREATE HTML REPORT SELECT INPUT  ***************** -->
	<!--  This is appropirate for an enumerated choice with numberic value associateion -->
	<xsl:template match="rpt:input-select">
		<p>
			<xsl:value-of select="rpt:title"/> :
			<select>
				<xsl:attribute name="name"><xsl:value-of select="rpt:label"/></xsl:attribute>
				<xsl:for-each select="rpt:option">
					<option>
						<xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute>
						<xsl:if test="@value=../rpt:init">
							<xsl:attribute name="selected"/>
						</xsl:if>
						<xsl:value-of select="."/>
					</option>
				</xsl:for-each>
			</select>
		</p>
	</xsl:template>


	<!--  CREATE HTML REPORT DATE INPUT  ***************** -->
	<!--  CREATE HTML REPORT DATE INPUT  ***************** -->
	<!--  This is appropriate for a three field date input -->
	<xsl:template match="rpt:input-date">

		<!-- Init field is expected to be "yyyy-mm-dd" : i.e. SQL format -->
		<xsl:variable name="day" select="substring-after( substring-after(rpt:init, '-'), '-')"/>
		<xsl:variable name="month" select="substring-before( substring-after(rpt:init, '-'), '-')"/>
		<xsl:variable name="year" select="substring-before(rpt:init, '-')"/>
		<p>
			<xsl:value-of select="rpt:title"/> : Day
			<select>
				<xsl:attribute name="name"><xsl:value-of select="rpt:label"/>-dd</xsl:attribute>
				<option value="1"><xsl:if test="$day='1'"><xsl:attribute name="selected"/></xsl:if>1</option>
				<option value="2"><xsl:if test="$day='2'"><xsl:attribute name="selected"/></xsl:if>2</option>
				<option value="3"><xsl:if test="$day='3'"><xsl:attribute name="selected"/></xsl:if>3</option>
				<option value="4"><xsl:if test="$day='4'"><xsl:attribute name="selected"/></xsl:if>4</option>
				<option value="5"><xsl:if test="$day='5'"><xsl:attribute name="selected"/></xsl:if>5</option>
				<option value="6"><xsl:if test="$day='6'"><xsl:attribute name="selected"/></xsl:if>6</option>
				<option value="7"><xsl:if test="$day='7'"><xsl:attribute name="selected"/></xsl:if>7</option>
				<option value="8"><xsl:if test="$day='8'"><xsl:attribute name="selected"/></xsl:if>8</option>
				<option value="9"><xsl:if test="$day='9'"><xsl:attribute name="selected"/></xsl:if>9</option>
				<option value="10"><xsl:if test="$day='10'"><xsl:attribute name="selected"/></xsl:if>10</option>
				<option value="11"><xsl:if test="$day='11'"><xsl:attribute name="selected"/></xsl:if>11</option>
				<option value="12"><xsl:if test="$day='12'"><xsl:attribute name="selected"/></xsl:if>12</option>
				<option value="13"><xsl:if test="$day='13'"><xsl:attribute name="selected"/></xsl:if>13</option>
				<option value="14"><xsl:if test="$day='14'"><xsl:attribute name="selected"/></xsl:if>14</option>
				<option value="15"><xsl:if test="$day='15'"><xsl:attribute name="selected"/></xsl:if>15</option>
				<option value="16"><xsl:if test="$day='16'"><xsl:attribute name="selected"/></xsl:if>16</option>
				<option value="17"><xsl:if test="$day='17'"><xsl:attribute name="selected"/></xsl:if>17</option>
				<option value="18"><xsl:if test="$day='18'"><xsl:attribute name="selected"/></xsl:if>18</option>
				<option value="19"><xsl:if test="$day='19'"><xsl:attribute name="selected"/></xsl:if>19</option>
				<option value="20"><xsl:if test="$day='20'"><xsl:attribute name="selected"/></xsl:if>10</option>
				<option value="21"><xsl:if test="$day='21'"><xsl:attribute name="selected"/></xsl:if>21</option>
				<option value="22"><xsl:if test="$day='22'"><xsl:attribute name="selected"/></xsl:if>22</option>
				<option value="23"><xsl:if test="$day='23'"><xsl:attribute name="selected"/></xsl:if>23</option>
				<option value="24"><xsl:if test="$day='24'"><xsl:attribute name="selected"/></xsl:if>24</option>
				<option value="25"><xsl:if test="$day='25'"><xsl:attribute name="selected"/></xsl:if>25</option>
				<option value="26"><xsl:if test="$day='26'"><xsl:attribute name="selected"/></xsl:if>26</option>
				<option value="27"><xsl:if test="$day='27'"><xsl:attribute name="selected"/></xsl:if>27</option>
				<option value="28"><xsl:if test="$day='28'"><xsl:attribute name="selected"/></xsl:if>28</option>
				<option value="29"><xsl:if test="$day='29'"><xsl:attribute name="selected"/></xsl:if>29</option>
				<option value="30"><xsl:if test="$day='30'"><xsl:attribute name="selected"/></xsl:if>30</option>
				<option value="31"><xsl:if test="$day='31'"><xsl:attribute name="selected"/></xsl:if>31</option>
			</select>
			<select>
				<xsl:attribute name="name"><xsl:value-of select="rpt:label"/>-mm</xsl:attribute>
				<option value="01"><xsl:if test="$month='01'"><xsl:attribute name="selected"/></xsl:if>Jan</option>
				<option value="02"><xsl:if test="$month='02'"><xsl:attribute name="selected"/></xsl:if>Feb</option>
				<option value="03"><xsl:if test="$month='03'"><xsl:attribute name="selected"/></xsl:if>Mar</option>
				<option value="04"><xsl:if test="$month='04'"><xsl:attribute name="selected"/></xsl:if>Apr</option>
				<option value="05"><xsl:if test="$month='05'"><xsl:attribute name="selected"/></xsl:if>May</option>
				<option value="06"><xsl:if test="$month='06'"><xsl:attribute name="selected"/></xsl:if>Jun</option>
				<option value="07"><xsl:if test="$month='07'"><xsl:attribute name="selected"/></xsl:if>Jul</option>
				<option value="08"><xsl:if test="$month='08'"><xsl:attribute name="selected"/></xsl:if>Aug</option>
				<option value="09"><xsl:if test="$month='09'"><xsl:attribute name="selected"/></xsl:if>Sep</option>
				<option value="10"><xsl:if test="$month='10'"><xsl:attribute name="selected"/></xsl:if>Oct</option>
				<option value="11"><xsl:if test="$month='11'"><xsl:attribute name="selected"/></xsl:if>Nov</option>
				<option value="12"><xsl:if test="$month='12'"><xsl:attribute name="selected"/></xsl:if>Dec</option>
			</select>
			<select>
				<xsl:attribute name="name"><xsl:value-of select="rpt:label"/>-yy</xsl:attribute>
				<option value="2000"><xsl:if test="$year='2000'"><xsl:attribute name="selected"/></xsl:if>2000</option>
				<option value="2001"><xsl:if test="$year='2001'"><xsl:attribute name="selected"/></xsl:if>2001</option>
				<option value="2002"><xsl:if test="$year='2002'"><xsl:attribute name="selected"/></xsl:if>2002</option>
				<option value="2003"><xsl:if test="$year='2003'"><xsl:attribute name="selected"/></xsl:if>2003</option>
				<option value="2004"><xsl:if test="$year='2004'"><xsl:attribute name="selected"/></xsl:if>2004</option>
				<option value="2005"><xsl:if test="$year='2005'"><xsl:attribute name="selected"/></xsl:if>2005</option>
			</select>
		</p>
	</xsl:template>

</xsl:stylesheet>