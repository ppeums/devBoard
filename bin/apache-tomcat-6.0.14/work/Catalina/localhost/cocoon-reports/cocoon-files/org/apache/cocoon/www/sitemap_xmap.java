
      
    /*****************************************************************************/
    /* Copyright (C) The Apache Software Foundation. All rights reserved.        */
    /* _________________________________________________________________________ */
    /* This software is published under the terms of the Apache Software License */
    /* version 1.1, a copy of which has been included  with this distribution in */
    /* the LICENSE file.                                                         */
    /*****************************************************************************/
    package org.apache.cocoon.www;

    import java.io.OutputStream;
    import java.io.IOException;

    import java.util.List;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.HashMap;
    import java.util.Stack;
    import java.util.StringTokenizer;

    import org.apache.avalon.framework.component.Component;
    import org.apache.avalon.framework.configuration.Configurable;
    import org.apache.avalon.framework.configuration.Configuration;
    import org.apache.avalon.framework.configuration.ConfigurationException;
    import org.apache.avalon.framework.configuration.DefaultConfiguration;
    import org.apache.avalon.framework.parameters.Parameters;

    import org.apache.cocoon.Constants;
    import org.apache.cocoon.ProcessingException;
    import org.apache.cocoon.ResourceNotFoundException;
    import org.apache.cocoon.ConnectionResetException;
    import org.apache.cocoon.acting.Action;
    import org.apache.cocoon.environment.Environment;
    import org.apache.cocoon.environment.Redirector;
    import org.apache.cocoon.matching.Matcher;
    import org.apache.cocoon.matching.PreparableMatcher;
    import org.apache.cocoon.selection.Selector;
    import org.apache.cocoon.sitemap.AbstractSitemap;
    import org.apache.cocoon.components.pipeline.StreamPipeline;
    import org.apache.cocoon.components.pipeline.EventPipeline;
    import org.apache.cocoon.sitemap.Sitemap;
    import org.apache.cocoon.sitemap.NotifyingGenerator;
    import org.apache.cocoon.sitemap.ContentAggregator;
    import org.apache.cocoon.sitemap.Manager;
    import org.apache.cocoon.sitemap.SitemapRedirector;
    import org.apache.cocoon.sitemap.SitemapComponentSelector;
    import org.apache.cocoon.components.language.markup.xsp.XSPRequestHelper;
    import org.apache.cocoon.components.language.markup.xsp.XSPResponseHelper;
    import org.apache.cocoon.components.notification.NotifyingBuilder;
    import org.apache.cocoon.components.notification.Notifying;
    import org.apache.cocoon.components.notification.SimpleNotifyingBean;

    /**
     * This is the automatically generated class from the sitemap definitions
     *
     * @author <a href="mailto:giacomo@apache.org">Giacomo Pati</a>
     * @author <a href="mailto:bloritsch@apache.org">Berin Loritsch</a>
     * @author <a href="mailto:barozzi@nicolaken.com">Nicola Ken Barozzi</a>
     * @author <a href="mailto:proyal@managingpartners.com">Peter Royal</a>
     * @version CVS $Id: sitemap.xsl,v 1.11.2.4 2002/06/27 18:52:01 vgritsenko Exp $
     */
    public class sitemap_xmap extends AbstractSitemap {
      static final String LOCATION = "org.apache.cocoon.www.sitemap_xmap";

      public sitemap_xmap () {
        dateCreated = 1255672686531L;
      }

      /** HashMap relating labels to view names */
      private HashMap view_label_map = new HashMap(3);

      
      // Pattern for "" (either String or prepared pattern)
      private Object matcher_N10096_expr;
      
      // Pattern for "report/*.rpt" (either String or prepared pattern)
      private Object matcher_N100AC_expr;
      
      // Pattern for "report/*.xls" (either String or prepared pattern)
      private Object matcher_N100BF_expr;
      

      /**
       * Method that handles selectors.
       */
      private boolean isSelected(String hint, String testValue, Parameters params, Map objectModel) throws Exception {
        Selector selector = (Selector)this.selectors.select(hint);
        try {
          return selector.select(testValue, objectModel, params);
        } finally {
          this.selectors.release(selector);
        }
      }

      /**
       * Method that handles matchers.
       */
      private Map matches(String hint, Object preparedPattern, String pattern, Parameters params, Map objectModel) throws Exception {
        Component matcher = (Component)this.matchers.select(hint);
        try {
          if (preparedPattern == null) {
            return ((Matcher)matcher).match(pattern, objectModel, params);
          } else {
            return ((PreparableMatcher)matcher).preparedMatch(preparedPattern, objectModel, params);
          }
        } finally {
          this.matchers.release(matcher);
        }
      }


      /**
       * Pass a <code>Configuration</code> instance to this
       * <code>Configurable</code> class.
       */
      public void configure(Configuration conf) throws ConfigurationException {
          this.sitemapManager = new Manager();
          this.sitemapManager.setLogger(getLogger());
          this.sitemapManager.compose(this.manager);
          this.sitemapManager.configure(conf);

        view_label_map.put("gen-request-lbl", "gen-request-view");
          view_label_map.put("tran-xslt-lbl", "tran-xslt-view");
          view_label_map.put("tran-xmlhttp-lbl", "tran-xmlhttp-view");
          

        try {
          
          load_component (Sitemap.GENERATOR, "!notifying-generator!", "org.apache.cocoon.sitemap.NotifyingGenerator", new DefaultConfiguration("", LOCATION), null);
          load_component (Sitemap.GENERATOR, "!content-aggregator!", "org.apache.cocoon.sitemap.ContentAggregator", new DefaultConfiguration("", LOCATION), null);
          load_component (Sitemap.TRANSFORMER, "!link-translator!", "org.apache.cocoon.sitemap.LinkTranslator", new DefaultConfiguration("", LOCATION), null);

          Configurer configurer = new Configurer(this, LOCATION);
          configurer.configGenerators();
          configurer.configTransformers();
          configurer.configReaders();
          configurer.configSerializers();
          configurer.configMatchers();
          configurer.configSelectors();
          configurer.configActions();
          configurer = null;

          this.generators.initialize();
          this.transformers.initialize();
          this.serializers.initialize();
          this.readers.initialize();
          this.actions.initialize();
          this.matchers.initialize();
          this.prepareMatchers();
          this.selectors.initialize();

          this.manager.initialize();
          /* catch any exception thrown by a component during configuration */
        } catch (Exception e) {
          getLogger().warn(e.getMessage(), e);
          throw new ConfigurationException ("Error in sitemap configuration : " + e.getMessage(), e);
        }
      }

      
      class Configurer {
        sitemap_xmap sitemap;
        String LOCATION;
        public Configurer (sitemap_xmap sitemap, String location) {
          this.sitemap = sitemap;
          this.LOCATION = location;
        }

        /** Configure generators */
        public void configGenerators() throws Exception {
          
//line numbers not supported with xalan
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("file", LOCATION);
        cconf1.addAttribute ("name", "file");
        cconf1.addAttribute ("label", "content,data");
        cconf1.addAttribute ("logger", "sitemap.generator.file");
        cconf1.addAttribute ("src", "org.apache.cocoon.generation.FileGenerator");
        
          sitemap.load_component (Sitemap.GENERATOR, "file", "org.apache.cocoon.generation.FileGenerator", cconf1, null);
      }
    
//line numbers not supported with xalan
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("directory", LOCATION);
        cconf1.addAttribute ("name", "directory");
        cconf1.addAttribute ("label", "gen-dir-lbl");
        cconf1.addAttribute ("src", "org.apache.cocoon.generation.DirectoryGenerator");
        
          sitemap.load_component (Sitemap.GENERATOR, "directory", "org.apache.cocoon.generation.DirectoryGenerator", cconf1, null);
      }
    
//line numbers not supported with xalan
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("request", LOCATION);
        cconf1.addAttribute ("name", "request");
        cconf1.addAttribute ("label", "data");
        cconf1.addAttribute ("logger", "sitemap.generator.request");
        cconf1.addAttribute ("pool-grow", "2");
        cconf1.addAttribute ("pool-max", "16");
        cconf1.addAttribute ("pool-min", "2");
        cconf1.addAttribute ("src", "org.apache.cocoon.generation.RequestGenerator");
        
          sitemap.load_component (Sitemap.GENERATOR, "request", "org.apache.cocoon.generation.RequestGenerator", cconf1, null);
      }
    
        }

        /** Configure transformers */
        public void configTransformers() throws Exception {
          
//line numbers not supported with xalan
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("xslt", LOCATION);
        cconf1.addAttribute ("name", "xslt");
        cconf1.addAttribute ("label", "tran-xslt-lbl");
        cconf1.addAttribute ("logger", "sitemap.transformer.xslt");
        cconf1.addAttribute ("src", "org.apache.cocoon.transformation.TraxTransformer");
        
//line numbers not supported with xalan
      {
         DefaultConfiguration cconf2 = new DefaultConfiguration("use-request-parameters", LOCATION);
      
        cconf2.appendValueData("false");
      
        cconf1.addChild(cconf2);
     
      }
    
//line numbers not supported with 1alan
      {
         DefaultConfiguration cconf2 = new DefaultConfiguration("use-browser-capabilities-db", LOCATION);
      
        cconf2.appendValueData("false");
      
        cconf1.addChild(cconf2);
     
      }
    
//line numbers not supported with 12lan
      {
         DefaultConfiguration cconf2 = new DefaultConfiguration("use-deli", LOCATION);
      
        cconf2.appendValueData("false");
      
        cconf1.addChild(cconf2);
     
      }
    
          sitemap.load_component (Sitemap.TRANSFORMER, "xslt", "org.apache.cocoon.transformation.TraxTransformer", cconf1, null);
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("sql", LOCATION);
        cconf1.addAttribute ("name", "sql");
        cconf1.addAttribute ("label", "tran-sql-lbl");
        cconf1.addAttribute ("logger", "sitemap.transformer.sql");
        cconf1.addAttribute ("src", "org.apache.cocoon.transformation.SQLTransformer");
        
          sitemap.load_component (Sitemap.TRANSFORMER, "sql", "org.apache.cocoon.transformation.SQLTransformer", cconf1, null);
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("xinclude", LOCATION);
        cconf1.addAttribute ("name", "xinclude");
        cconf1.addAttribute ("label", "tran-xinclude-lbl");
        cconf1.addAttribute ("logger", "sitemap.transformer.xinclude");
        cconf1.addAttribute ("pool-grow", "2");
        cconf1.addAttribute ("pool-max", "16");
        cconf1.addAttribute ("pool-min", "2");
        cconf1.addAttribute ("src", "org.apache.cocoon.transformation.XIncludeTransformer");
        
          sitemap.load_component (Sitemap.TRANSFORMER, "xinclude", "org.apache.cocoon.transformation.XIncludeTransformer", cconf1, null);
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("sql", LOCATION);
        cconf1.addAttribute ("name", "sql");
        cconf1.addAttribute ("label", "tran-sql-lbl");
        cconf1.addAttribute ("logger", "sitemap.transformer.sql");
        cconf1.addAttribute ("src", "org.apache.cocoon.transformation.SQLTransformer");
        
          sitemap.load_component (Sitemap.TRANSFORMER, "sql", "org.apache.cocoon.transformation.SQLTransformer", cconf1, null);
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("log", LOCATION);
        cconf1.addAttribute ("label", "tran-log-lbl");
        cconf1.addAttribute ("logger", "sitemap.transformer.log");
        cconf1.addAttribute ("name", "log");
        cconf1.addAttribute ("src", "org.apache.cocoon.transformation.LogTransformer");
        
          sitemap.load_component (Sitemap.TRANSFORMER, "log", "org.apache.cocoon.transformation.LogTransformer", cconf1, null);
      }
    
        }

        /** Configure readers */
        public void configReaders() throws Exception {
          
        }

        /* Configure serializers */
        public void configSerializers() throws Exception {
          
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("xml", LOCATION);
        cconf1.addAttribute ("name", "xml");
        cconf1.addAttribute ("logger", "sitemap.serializer.xml");
        cconf1.addAttribute ("mime-type", "text/xml");
        cconf1.addAttribute ("src", "org.apache.cocoon.serialization.XMLSerializer");
        
          sitemap.load_component (Sitemap.SERIALIZER, "xml", "org.apache.cocoon.serialization.XMLSerializer", cconf1, "text/xml");
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("html", LOCATION);
        cconf1.addAttribute ("name", "html");
        cconf1.addAttribute ("logger", "sitemap.serializer.html");
        cconf1.addAttribute ("mime-type", "text/html");
        cconf1.addAttribute ("pool-grow", "4");
        cconf1.addAttribute ("pool-max", "32");
        cconf1.addAttribute ("pool-min", "4");
        cconf1.addAttribute ("src", "org.apache.cocoon.serialization.HTMLSerializer");
        
//line numbers not supported with 123an
      {
         DefaultConfiguration cconf2 = new DefaultConfiguration("buffer-size", LOCATION);
      
        cconf2.appendValueData("1024");
      
        cconf1.addChild(cconf2);
     
      }
    
          sitemap.load_component (Sitemap.SERIALIZER, "html", "org.apache.cocoon.serialization.HTMLSerializer", cconf1, "text/html");
      }
    
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("excel_serializer", LOCATION);
        cconf1.addAttribute ("name", "excel-serializer");
        cconf1.addAttribute ("logger", "sitemap.serializer.excel");
        cconf1.addAttribute ("src", "com.clsw.cocoon.ReportSerializer");
        
          sitemap.load_component (Sitemap.SERIALIZER, "excel-serializer", "com.clsw.cocoon.ReportSerializer", cconf1, null);
      }
    
        }

        /** Configure matchers */
        public void configMatchers() throws Exception {
          
//line numbers not supported with 123an
      {
        DefaultConfiguration cconf1 = new DefaultConfiguration("wildcard", LOCATION);
        cconf1.addAttribute ("logger", "sitemap.matcher.wildcard");
        cconf1.addAttribute ("name", "wildcard");
        cconf1.addAttribute ("src", "org.apache.cocoon.matching.WildcardURIMatcher");
        
          sitemap.load_component (Sitemap.MATCHER, "wildcard", "org.apache.cocoon.matching.WildcardURIMatcher", cconf1, null);
      }
    
        }

        /** Configure selectors */
        public void configSelectors() throws Exception {
          
        }

        /** Configure actions */
        public void configActions() throws Exception {
          
        }
      }

      public Object preparePattern(String type, String pattern) throws Exception {
        Component matcher = this.matchers.select(type);
        try {
          if (matcher instanceof PreparableMatcher) {
            // Prepare pattern ('{' have been unescaped)
            return ((PreparableMatcher)matcher).preparePattern(pattern);
          } else {
            // Return null : regular Matcher will be used.
            return null;
          }
        } finally {
          this.matchers.release(matcher);
        }
      }

      /** Prepare patterns of PreparableMatchers. */
      public void prepareMatchers() throws Exception {
      
      
          // Prepare the pattern for ""
          this.matcher_N10096_expr = this.preparePattern("wildcard", "");
          
          // Prepare the pattern for "report/*.rpt"
          this.matcher_N100AC_expr = this.preparePattern("wildcard", "report/*.rpt");
          
          // Prepare the pattern for "report/*.xls"
          this.matcher_N100BF_expr = this.preparePattern("wildcard", "report/*.xls");
          
      }

      
      
//line numbers not supported with wildc
        /**
         * This is the method to produce the "gen-request-view" view of the requested resource
         * @param pipeline A <code>StreamPipeline</code> holding the sitemap component collected so far
         * @param listOfMaps A <code>List</code> of Maps holding replacement values for src attributes
         * @param environment The <code>Environment</code> requesting a resource
         * @return Wether the request has been processed or not
         * @exception Exception If an error occurs during request evaluation and production
         */
        private final boolean view_gen_request_view (StreamPipeline pipeline,
            EventPipeline eventPipeline, List listOfMaps, Environment environment, boolean internalRequest)
        throws Exception {
          Map map = null;
          Parameters param = null;
          final boolean debug_enabled = getLogger().isDebugEnabled();
          Map objectModel = environment.getObjectModel();
          SitemapRedirector redirector = new SitemapRedirector(environment);
          
			

    getLogger().debug("Component serializer:xml(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("xml",
              null, Parameters.EMPTY_PARAMETERS
            );
          
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  
		
          return internalRequest;
        }
      
//line numbers not supported with wildc
        /**
         * This is the method to produce the "tran-xslt-view" view of the requested resource
         * @param pipeline A <code>StreamPipeline</code> holding the sitemap component collected so far
         * @param listOfMaps A <code>List</code> of Maps holding replacement values for src attributes
         * @param environment The <code>Environment</code> requesting a resource
         * @return Wether the request has been processed or not
         * @exception Exception If an error occurs during request evaluation and production
         */
        private final boolean view_tran_xslt_view (StreamPipeline pipeline,
            EventPipeline eventPipeline, List listOfMaps, Environment environment, boolean internalRequest)
        throws Exception {
          Map map = null;
          Parameters param = null;
          final boolean debug_enabled = getLogger().isDebugEnabled();
          Map objectModel = environment.getObjectModel();
          SitemapRedirector redirector = new SitemapRedirector(environment);
          
			

    getLogger().debug("Component serializer:xml(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("xml",
              null, Parameters.EMPTY_PARAMETERS
            );
          
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  
		
          return internalRequest;
        }
      
//line numbers not supported with wildc
        /**
         * This is the method to produce the "tran-xmlhttp-view" view of the requested resource
         * @param pipeline A <code>StreamPipeline</code> holding the sitemap component collected so far
         * @param listOfMaps A <code>List</code> of Maps holding replacement values for src attributes
         * @param environment The <code>Environment</code> requesting a resource
         * @return Wether the request has been processed or not
         * @exception Exception If an error occurs during request evaluation and production
         */
        private final boolean view_tran_xmlhttp_view (StreamPipeline pipeline,
            EventPipeline eventPipeline, List listOfMaps, Environment environment, boolean internalRequest)
        throws Exception {
          Map map = null;
          Parameters param = null;
          final boolean debug_enabled = getLogger().isDebugEnabled();
          Map objectModel = environment.getObjectModel();
          SitemapRedirector redirector = new SitemapRedirector(environment);
          
			

    getLogger().debug("Component serializer:xml(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("xml",
              null, Parameters.EMPTY_PARAMETERS
            );
          
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  
		
          return internalRequest;
        }
      

    
    private final boolean check_view(String labels, String cocoon_view) {
        if (cocoon_view == null) return false;
        final boolean debug_enabled = getLogger().isDebugEnabled();

        StringTokenizer st = new StringTokenizer(labels, " ,", false);
        while (st.hasMoreTokens()) {
          String token = st.nextToken();
          String view = (String)view_label_map.get(token);
          if (view != null && view.equals(cocoon_view)) {
            if (debug_enabled) getLogger().debug("check_view: view \"" + view + "\" selected");
            return true;
          }
        }
        return false;
    }

    
    private final boolean check_view(SitemapComponentSelector selector, String hint, String labels, String cocoon_view) {
        if (cocoon_view == null) return false;
        final boolean debug_enabled = getLogger().isDebugEnabled();

        if (labels != null) {
            // Check labels provided on component invocation
            StringTokenizer st = new StringTokenizer(labels, " ,", false);
            while (st.hasMoreTokens()) {
                String view = (String)view_label_map.get(st.nextToken());
                if (view != null && view.equals(cocoon_view)) {
                    if (debug_enabled) getLogger().debug("check_view: View \"" + view + "\" selected");
                    return true;
                }
            }
        }

        String[] aLabels = selector.getLabels(hint);
        if (aLabels != null) {
            // Check labels provided on component declaration
            for (int i=0; i < aLabels.length; i++) {
                String view = (String)view_label_map.get(aLabels[i]);
                if (view != null && view.equals(cocoon_view)) {
                    if (debug_enabled) getLogger().debug("check_view: View \"" + view + "\" selected");
                    return true;
                }
            }
        }
        return false;
    }

      
      private final boolean call_view(String view_name,
                                StreamPipeline pipeline,
                                EventPipeline eventPipeline,
                                List listOfMaps,
                                Environment environment,
                                boolean internalRequest)
      throws Exception {
        if (getLogger().isDebugEnabled()) getLogger().debug("call_view(\"" + view_name + "\")");
        if ("gen-request-view".equals(view_name)) {
            return view_gen_request_view(pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }
        if ("tran-xslt-view".equals(view_name)) {
            return view_tran_xslt_view(pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }
        if ("tran-xmlhttp-view".equals(view_name)) {
            return view_tran_xmlhttp_view(pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }
        
        return internalRequest;
      }

      

      /**
       * Process to producing the output to the specified <code>OutputStream</code>.
       */
      public final boolean process(Environment environment) throws Exception {
        /* the <code>EventPipeline</code> is used to collect the xml producing sitemap
           components and the <code>StreamPipeline</code> to produce the requested resource */
        EventPipeline eventPipeline = null;
        StreamPipeline pipeline = null;
        boolean result = false;
        try {
          try {
            eventPipeline = (EventPipeline)this.manager.lookup(EventPipeline.ROLE);
            pipeline = (StreamPipeline)this.manager.lookup(StreamPipeline.ROLE);
            pipeline.setEventPipeline(eventPipeline);
          } catch (Exception e) {
            getLogger().error("Processing of resource failed", e);
            throw e;
          }

          result = process (environment, pipeline, eventPipeline, false);

        } finally {
          if(eventPipeline != null) this.manager.release(eventPipeline);
          if(pipeline != null) this.manager.release(pipeline);
        }
        return result;
      }

      /**
       * Process to producing the output to the specified <code>OutputStream</code>.
       */
      public final boolean process(Environment environment, StreamPipeline pipeline, EventPipeline eventPipeline)
      throws Exception {
        getLogger().debug("Processing internal sitemap request");
        return process (environment, pipeline, eventPipeline, true);
      }

      /**
       * Process to producing the output to the specified <code>OutputStream</code>.
       */
      private final boolean process(Environment environment, StreamPipeline pipeline,
                                    EventPipeline eventPipeline, boolean internalRequest)
      throws Exception {
        // set the correct component manager
        pipeline.recompose(this.manager);
        eventPipeline.recompose(this.manager);

        /* the <code>List</code> objects to hold the replacement values
           delivered from matchers and selectors to replace occurences of
           XPath kind expressions in values of src attribute used with
           generate and transform elements */
        List listOfMaps = (List) new ArrayList();

        Map map;
        Parameters param;
        Map objectModel = environment.getObjectModel();
        String cocoon_view = environment.getView();
        String cocoon_action = environment.getAction();
        final boolean debug_enabled = getLogger().isDebugEnabled();

        //Adds Constants.NOTIFYING_OBJECT to ObjectModel
        //NKB FIXME add to ObjectModel?
        objectModel.put(Constants.NOTIFYING_OBJECT, new SimpleNotifyingBean(this));
        SitemapRedirector redirector = new SitemapRedirector(environment);
        
        
        
//line numbers not supported with wildc
          try {
            
            

			
    	
    // method for handling ""
    if (matchN10096(redirector, environment, pipeline, eventPipeline, internalRequest, listOfMaps))
        return true;
  


    	
    	
    // method for handling "report/*.rpt"
    if (matchN100AC(redirector, environment, pipeline, eventPipeline, internalRequest, listOfMaps))
        return true;
  


    	
    	
    // method for handling "report/*.xls"
    if (matchN100BF(redirector, environment, pipeline, eventPipeline, internalRequest, listOfMaps))
        return true;
  

		
              throw new ResourceNotFoundException("No pipeline matched request: " + environment.getURIPrefix() + environment.getURI());
            
          } catch (ConnectionResetException cre) {
             // Will be reported by CocoonServlet, rethrowing
            throw cre;
          } catch (ResourceNotFoundException e) {
            
                // Will be reported by CocoonServlet, rethrowing
                if (true) throw e;
              
          } catch (Exception e) {
            
                if (getLogger().isErrorEnabled()) getLogger().error("Sitemap", e);
                if (true) throw e;
              
          }
          

        return false;
      }

      // method for handling ""
    private final boolean matchN10096(
                                    SitemapRedirector redirector,
                                    Environment environment, StreamPipeline pipeline,
                                    EventPipeline eventPipeline,
                                    boolean internalRequest, List listOfMaps)
            throws ConnectionResetException, ResourceNotFoundException, Exception {
        Map map;
        Parameters param;
        Map objectModel = environment.getObjectModel();
        String cocoon_view = environment.getView();
        String cocoon_action = environment.getAction();
        final boolean debug_enabled = getLogger().isDebugEnabled();

        
        

        if ((map = matches("wildcard", matcher_N10096_expr, "", Parameters.EMPTY_PARAMETERS, objectModel)) != null) {
          if (debug_enabled) getLogger().debug("Matched wildcard pattern ");
          listOfMaps.add (map);
          this.dumpParameters(listOfMaps);
          

    		
    		

    getLogger().debug("Component generator:directory(Parameters.EMPTY_PARAMETERS)");
    
        if (debug_enabled) getLogger().debug("Source= " + "reports");
        eventPipeline.setGenerator ("directory",
              "reports",Parameters.EMPTY_PARAMETERS);
          
          { boolean has_view = check_view(generators, "directory", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
    		

    getLogger().debug("Component transformer:xslt(Parameters.EMPTY_PARAMETERS)");
    
        if (debug_enabled) getLogger().debug("Source= " + "list-logic.xsl");
        eventPipeline.addTransformer ("xslt",
              "list-logic.xsl",Parameters.EMPTY_PARAMETERS);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				

    getLogger().debug("Component transformer:xinclude(Parameters.EMPTY_PARAMETERS)");
    eventPipeline.addTransformer ("xinclude",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(transformers, "xinclude", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
    		

    getLogger().debug("Component transformer:xslt(Parameters.EMPTY_PARAMETERS)");
    
        if (debug_enabled) getLogger().debug("Source= " + "list-logic.xsl");
        eventPipeline.addTransformer ("xslt",
              "list-logic.xsl",Parameters.EMPTY_PARAMETERS);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

 				
    		
        // performing link translation
        if (environment.getObjectModel().containsKey(Constants.LINK_OBJECT)) {
            eventPipeline.addTransformer ("!link-translator!", null, Parameters.EMPTY_PARAMETERS);
        }
      

    getLogger().debug("Component serializer:html(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("html",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(serializers, "html", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  

    	
          listOfMaps.remove (listOfMaps.size()-1);
          this.dumpParameters(listOfMaps);
        }
        return false;
    }
  // method for handling "report/*.rpt"
    private final boolean matchN100AC(
                                    SitemapRedirector redirector,
                                    Environment environment, StreamPipeline pipeline,
                                    EventPipeline eventPipeline,
                                    boolean internalRequest, List listOfMaps)
            throws ConnectionResetException, ResourceNotFoundException, Exception {
        Map map;
        Parameters param;
        Map objectModel = environment.getObjectModel();
        String cocoon_view = environment.getView();
        String cocoon_action = environment.getAction();
        final boolean debug_enabled = getLogger().isDebugEnabled();

        
        

        if ((map = matches("wildcard", matcher_N100AC_expr, "report/*.rpt", Parameters.EMPTY_PARAMETERS, objectModel)) != null) {
          if (debug_enabled) getLogger().debug("Matched wildcard pattern report/*.rpt");
          listOfMaps.add (map);
          this.dumpParameters(listOfMaps);
          

    		
    		

    getLogger().debug("Component generator:file(Parameters.EMPTY_PARAMETERS)");
    
        if (debug_enabled) getLogger().debug("Source= " + substitute(listOfMaps, "reports/{1}/scheme.xml"));
        eventPipeline.setGenerator ("file",
              substitute(listOfMaps, "reports/{1}/scheme.xml"),Parameters.EMPTY_PARAMETERS);
          
          { boolean has_view = check_view(generators, "file", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				
      param = new Parameters ();
      param.setParameter ("label", substitute(listOfMaps, "{1}"));
    

    getLogger().debug("Component transformer:xslt(param)");
    
        if (debug_enabled) getLogger().debug("Source= " + "param-logic.xsl");
        eventPipeline.addTransformer ("xslt",
              "param-logic.xsl",param);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

 				
    		
        // performing link translation
        if (environment.getObjectModel().containsKey(Constants.LINK_OBJECT)) {
            eventPipeline.addTransformer ("!link-translator!", null, Parameters.EMPTY_PARAMETERS);
        }
      

    getLogger().debug("Component serializer:html(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("html",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(serializers, "html", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  

    	
          listOfMaps.remove (listOfMaps.size()-1);
          this.dumpParameters(listOfMaps);
        }
        return false;
    }
  // method for handling "report/*.xls"
    private final boolean matchN100BF(
                                    SitemapRedirector redirector,
                                    Environment environment, StreamPipeline pipeline,
                                    EventPipeline eventPipeline,
                                    boolean internalRequest, List listOfMaps)
            throws ConnectionResetException, ResourceNotFoundException, Exception {
        Map map;
        Parameters param;
        Map objectModel = environment.getObjectModel();
        String cocoon_view = environment.getView();
        String cocoon_action = environment.getAction();
        final boolean debug_enabled = getLogger().isDebugEnabled();

        
        

        if ((map = matches("wildcard", matcher_N100BF_expr, "report/*.xls", Parameters.EMPTY_PARAMETERS, objectModel)) != null) {
          if (debug_enabled) getLogger().debug("Matched wildcard pattern report/*.xls");
          listOfMaps.add (map);
          this.dumpParameters(listOfMaps);
          

				
				
				

    getLogger().debug("Component generator:request(Parameters.EMPTY_PARAMETERS)");
    eventPipeline.setGenerator ("request",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(generators, "request", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				
      param = new Parameters ();
      param.setParameter ("label", substitute(listOfMaps, "{1}"));
    

    getLogger().debug("Component transformer:xslt(param)");
    
        if (debug_enabled) getLogger().debug("Source= " + "render-logic.xsl");
        eventPipeline.addTransformer ("xslt",
              "render-logic.xsl",param);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				 
				

    getLogger().debug("Component transformer:xinclude(Parameters.EMPTY_PARAMETERS)");
    eventPipeline.addTransformer ("xinclude",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(transformers, "xinclude", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				

    getLogger().debug("Component transformer:xslt(Parameters.EMPTY_PARAMETERS)");
    
        if (debug_enabled) getLogger().debug("Source= " + "render-logic.xsl");
        eventPipeline.addTransformer ("xslt",
              "render-logic.xsl",Parameters.EMPTY_PARAMETERS);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				
      param = new Parameters ();
      param.setParameter ("dburl", "jdbc:mysql://localhost/test");
    

    getLogger().debug("Component transformer:sql(param)");
    eventPipeline.addTransformer ("sql",
              null, param
            );
          
          { boolean has_view = check_view(transformers, "sql", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        

				
				
				
      param = new Parameters ();
      param.setParameter ("label", substitute(listOfMaps, "{1}"));
    param.setParameter ("absolute-path", "C:/opt/jakarta-tomcat-4.1.12/webapps/cocoon-reports");
    

    getLogger().debug("Component transformer:xslt(param)");
    
        if (debug_enabled) getLogger().debug("Source= " + "render-logic2.xsl");
        eventPipeline.addTransformer ("xslt",
              "render-logic2.xsl",param);
          
          { boolean has_view = check_view(transformers, "xslt", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        
				
 				
				
        // performing link translation
        if (environment.getObjectModel().containsKey(Constants.LINK_OBJECT)) {
            eventPipeline.addTransformer ("!link-translator!", null, Parameters.EMPTY_PARAMETERS);
        }
      

    getLogger().debug("Component serializer:excel-serializer(Parameters.EMPTY_PARAMETERS)");
    pipeline.setSerializer ("excel-serializer",
              null, Parameters.EMPTY_PARAMETERS
            );
          
          { boolean has_view = check_view(serializers, "excel-serializer", null, cocoon_view);
          if (has_view) {
              return call_view(cocoon_view, pipeline, eventPipeline, listOfMaps, environment, internalRequest);
          }}
        
      if (!internalRequest) {
        return pipeline.process(environment);
      }
    
    
    if (true) return true;
  

 				

    	
          listOfMaps.remove (listOfMaps.size()-1);
          this.dumpParameters(listOfMaps);
        }
        return false;
    }
  
//line numbers not supported with wildc
    }
  
    