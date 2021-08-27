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
   -   This stylesheet is use in two location of the root URI pipeline:
       fist to create xinclude query statemets based upon what the
       filesystem directory browser generater found, and second
       to translates the availalbe report and their titles into
       some reasonable looking HTML.
   -->
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:dir="http://apache.org/cocoon/directory/2.0"
		xmlns:xi="http://www.w3.org/2001/XInclude"
		xmlns:rpt="http://cocoon.clsw.com/report">


	<!--  CREATE XINCLUDE QUERY STATEMENTS  ***************** -->
	<!--  CREATE XINCLUDE QUERY STATEMENTS  ***************** -->
	<!--  Used at FIRST instance of xslt in list reports pipeline -->
	<xsl:template match="/dir:directory">
		<rpt:reports>
    	<xsl:for-each select="dir:directory">
    		<rpt:report>
    			<rpt:label><xsl:value-of select="@name"/></rpt:label>
    			<xi:include>
    				<xsl:attribute name="href">reports/<xsl:value-of select="@name"/>/scheme.xml#xpointer(/rpt:report/rpt:title)</xsl:attribute>
    			</xi:include>
    		</rpt:report>
    	</xsl:for-each>
    </rpt:reports>
	</xsl:template>


	<!--  CREATE HTML PAGE  ***************** -->
	<!--  CREATE HTML PAGE  ***************** -->
	<!--  Used at SECOND instance of xslt in list reports pipeline -->
	<xsl:template match="/rpt:reports">
		<html>
  		<body>
  			<h1>Awesome Report Generation On Apache Cocoon</h1>
  			<p>Available reports are show below:</p>
      	<xsl:for-each select="rpt:report">
      		<p>
      			<a>
      				<xsl:attribute name="href">report/<xsl:value-of select="rpt:label"/>.rpt</xsl:attribute>
      				<xsl:value-of select="rpt:title"/>
      			</a>
      		</p>
      	</xsl:for-each>
     </body>
   </html>
	</xsl:template>

</xsl:stylesheet>