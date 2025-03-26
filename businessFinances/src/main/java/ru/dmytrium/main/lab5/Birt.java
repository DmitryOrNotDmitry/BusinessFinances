package ru.dmytrium.main.lab5;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class Birt {

    @Autowired
    private ResourceLoader resourceLoader;

    private IReportEngine reportEngine;

    @PostConstruct
    protected void init() throws BirtException {
        EngineConfig config = new EngineConfig();
        Platform.startup(config);
        IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        reportEngine = factory.createReportEngine(config);
        System.out.println(reportEngine);
    }

    @PreDestroy
    public void destroy() {
        reportEngine.destroy();
        Platform.shutdown();
    }

    @Autowired
    private DataSource dataSource;

    private String getAbsoluteResourcePath(String relativePath) {
        try {
            Resource resource = resourceLoader.getResource(relativePath);
            return resource.getFile().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void generateUsersReport(String id, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("p_id", id == null || id.isEmpty() ? null : Integer.parseInt(id));

        generatePDF(response, request,
                getAbsoluteResourcePath("classpath:static/birt/users.rptdesign"), reportParams);
    }

    public void generateTransactionsReport(String businessId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("business_id", businessId == null || businessId.isEmpty() ? null : Integer.parseInt(businessId));

        generatePDF(response, request,
                getAbsoluteResourcePath("classpath:static/birt/trans_summary.rptdesign"), reportParams);
    }

    private void generatePDF(HttpServletResponse response,
                            HttpServletRequest request,
                            String reportPatternUrl,
                            Map<String, Object> reportParams) {
        IRunAndRenderTask task = null;

        try {
            IReportRunnable reportDesign = reportEngine.openReportDesign(reportPatternUrl);

            task = reportEngine.createRunAndRenderTask(reportDesign);

            task.setParameterValues(reportParams);

            response.setContentType(reportEngine.getMIMEType("pdf"));

            PDFRenderOption pdfRenderOption = new PDFRenderOption();
            pdfRenderOption.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);

            task.setRenderOption(pdfRenderOption);
            task.getAppContext().put(EngineConstants.APPCONTEXT_PDF_RENDER_CONTEXT, request);
            task.getAppContext().put("OdaJDBCDriverPassInConnection", dataSource.getConnection());

            pdfRenderOption.setOutputStream(response.getOutputStream());
            task.run();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            task.close();
        }
    }
}

