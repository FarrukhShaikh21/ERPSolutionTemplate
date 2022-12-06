package erpsoltemp.erpsoltempview.erptempclass;

import erpsolglob.erpsolglobmodel.erpsolglobclasses.ERPSolGlobClassModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.share.ADFContext;
import oracle.adf.model.BindingContext;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class ERPSolutionTemplateBean {
    private String lERPSolActivityName;
    private String lERPSolActionRuntime;//runtime activite name, this will be opened
    private String lERPSolAllowEdit;
    private String lERPSolAllowAdd;
    private String lERPSolAllowDelete;
    private String lERPSolAllowCancel;
    private String lERPSolAllowSubmit;
    private String lERPSolAllowUnSubmit;
    private String lERPSolIsAllow;
    private String lERPSolAllowPrint;
    private String lERPSolHLevel;
    private String lERPSolModuleId;//MODULE_ACTION id from SYS_PROGRAM
    private String lERPSolUserCode;
    private String lERPSolLocationCode;
    private String lERPSolStoreId;
    private String lERPSolRegionId;
    private String lERPShowMenu="ERPSOLYES";
    private String lERPSolProjectId;
    private String pERPUrl;
    public ERPSolutionTemplateBean() {
        super();
    }


    public void setLERPSolActivityName(String lERPSolActivityName) {
        this.lERPSolActivityName = lERPSolActivityName;
    }

    public String getLERPSolActivityName() {
        return lERPSolActivityName;
    }

    public void setLERPSolActionRuntime(String lERPSolActionRuntime) {
        this.lERPSolActionRuntime = lERPSolActionRuntime;
    }

    public String getLERPSolActionRuntime() {
        return lERPSolActionRuntime;
    }

    public void setLERPSolAllowEdit(String lERPSolAllowEdit) {
        this.lERPSolAllowEdit = lERPSolAllowEdit;
    }

    public String getLERPSolAllowEdit() {
        return lERPSolAllowEdit;
    }

    public void setLERPSolAllowAdd(String lERPSolAllowAdd) {
        this.lERPSolAllowAdd = lERPSolAllowAdd;
    }

    public String getLERPSolAllowAdd() {
        return lERPSolAllowAdd;
    }

    public void setLERPSolAllowDelete(String lERPSolAllowDelete) {
        this.lERPSolAllowDelete = lERPSolAllowDelete;
    }

    public String getLERPSolAllowDelete() {
        return lERPSolAllowDelete;
    }

    public void setLERPSolAllowCancel(String lERPSolAllowCancel) {
        this.lERPSolAllowCancel = lERPSolAllowCancel;
    }

    public String getLERPSolAllowCancel() {
        return lERPSolAllowCancel;
    }

    public void setLERPSolAllowSubmit(String lERPSolAllowSubmit) {
        this.lERPSolAllowSubmit = lERPSolAllowSubmit;
    }

    public String getLERPSolAllowSubmit() {
        return lERPSolAllowSubmit;
    }

    public void setLERPSolAllowUnSubmit(String lERPSolAllowUnSubmit) {
        this.lERPSolAllowUnSubmit = lERPSolAllowUnSubmit;
    }

    public String getLERPSolAllowUnSubmit() {
        return lERPSolAllowUnSubmit;
    }

    public void setLERPSolIsAllow(String lERPSolIsAllow) {
        this.lERPSolIsAllow = lERPSolIsAllow;
    }

    public String getLERPSolIsAllow() {
        return lERPSolIsAllow;
    }

    public void setLERPSolAllowPrint(String lERPSolAllowPrint) {
        this.lERPSolAllowPrint = lERPSolAllowPrint;
    }

    public String getLERPSolAllowPrint() {
        return lERPSolAllowPrint;
    }


    public void setLERPSolModuleId(String lERPSolModuleId) {
        this.lERPSolModuleId = lERPSolModuleId;
    }

    public String getLERPSolModuleId() {
        return lERPSolModuleId;
    }


    public void setLERPSolHLevel(String lERPSolHLevel) {
        this.lERPSolHLevel = lERPSolHLevel;
    }

    public String getLERPSolHLevel() {
        return lERPSolHLevel;
    }

    public String doERPSolGotoActivity() {
        try {
            System.out.println(ERPSolGlobClassModel.doGetUserCode());
        } catch (Exception e) {
            System.out.println("get user code exception");
            // TODO: Add catch code
            e.printStackTrace();
        }
        doSetErpActivityRights(this.lERPSolModuleId);
        try {
            if (lERPSolActionRuntime.toUpperCase().contains(".FMX")) {
                doErpSolOpenNewTab(pERPUrl);
                return null;
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return "ACTStartERPSolutionTaskFlow";
    }
  
    public void doSetErpActivityRights(String pModuleId) {
        System.out.println("this-is-doGetTransActivity-a");

//        lERPSolModuleId = pModuleId;//getOTERPModuleAction().getValue().toString(); //MODULE_ACTION id from SYS_MODULE_DETAIL
        System.out.println("this-is-doGetTransActivity-b");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_MODULE_ACTION",this.lERPSolModuleId);
        System.out.println("this-is-doGetTransActivity-c");
        DCBindingContainer bc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        System.out.println("this-is-doGetTransActivity-d");
    
        DCDataControl dc = bc.getDataControl();
        System.out.println("this-is-doGetTransActivity-e");
        ApplicationModule am = dc.getApplicationModule();
        ViewObject vo = am.findViewObject("ERPSolQvoRights");
        if (vo != null) {
            vo.remove();
        }
        System.out.println("this-is-doGetTransActivity-E");
        
        System.out.println("temp-1");
//        System.out.println(lERPModuleAction+ "lERPModuleAction");
//        System.out.println("SELECT MAX(A.ALLOW_ADD) ALLOW_ADD,MAX(A.ALLOW_DELETE) ALLOW_DELETE,MAX(A.ACTION_RUNTIME) ACTION_RUNTIME,MAX(A.IS_ALLOW) IS_ALLOW,MAX(A.ALLOW_EDIT) ALLOW_EDIT,MAX(A.ALLOW_SUPERVISE) ALLOW_SUPERVISE ,MAX(A.ALLOW_UNSUPERVISE) ALLOW_UNSUPERVISE,MAX(A.ALLOW_CANCEL) ALLOW_CANCEL, MAX(A.ALLOW_EDIT_OTHER) ALLOW_EDIT_OTHER, MAX(A.ALLOW_PRINT) ALLOW_PRINT,MAX(A.SCAN_FILE) SCAN_FILE   FROM sys_users_detail A WHERE USERID='"+ERPGlobalsClass.doGetUserSno()+"-"+lERPSolModuleId+"'");
        vo =
            am.createViewObjectFromQueryStmt("ERPSolQvoRights",
                                         "SELECT NVL(A.CANADD,'N') ALLOW_ADD," +/*0*/
                                         "NVL(A.CANDELETE,'N') ALLOW_DELETE," +/*1*/
                                         "NVL(A.RESTRICT_ACCESS,'Y') IS_ALLOW," +/*2*/
                                         "NVL(A.CANEDIT,'N') ALLOW_EDIT," + /*3*/
                                         "NVL(A.SUBMIT,'N') ALLOW_SUPERVISE ," +/*4*/
                                         "NVL(A.UNSUBMIT,'N') ALLOW_UNSUPERVISE," +/*5*/
                                         "NVL(A.PRINT,'N') ALLOW_PRINT, " +/*6*/
                                         "NVL(A.H_LEVEL,'U') H_LEVEL, " +/*7*/
                                         "NVL(A.PROJECTID,'-') PROJECTID " +/*8*/
                                         
                                         "FROM SYS_USERS_DETAIL A "+
                                         "WHERE USERID='"+ERPSolGlobClassModel.doGetUserCode()+"'" +
                                         //"WHERE USERID='"+"ORACLE"+"'" +
                                         " AND A.RESTRICT_ACCESS='N' " +
                                         " AND MODULEID='"+getLERPSolModuleId()+"'");
        System.out.println("temp-2");
        vo.executeQuery();
        System.out.println("lErpActivityRuntime"+ "ERP-ACT-"+lERPSolActionRuntime);
        System.out.println("temp-3");
        if (vo.getRowCount()==0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You Don't Have Rights on This Activity."));
            return;
//            throw new JboException("You Don't Have Rights on This Activity.");
       }
        
        System.out.println("temp-4");
        this.lERPSolAllowAdd = vo.first().getAttribute(0).toString();
        System.out.println("temp-5");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_ADD",lERPSolAllowAdd);
        System.out.println("temp-6");
        this.lERPSolAllowDelete = vo.first().getAttribute(1).toString();
        System.out.println("temp-7");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_DELETE",lERPSolAllowDelete);
        System.out.println("temp-8");
        
        this.lERPSolIsAllow = vo.first().getAttribute(2).toString();
        System.out.println("temp-9");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_IS_ALLOW",lERPSolIsAllow);
        System.out.println("temp-10");

        this.lERPSolAllowEdit = vo.first().getAttribute(3).toString();
        System.out.println("temp-11");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_EDIT",lERPSolAllowEdit);
        System.out.println("temp-12");
        this.lERPSolAllowSubmit = vo.first().getAttribute(4).toString();
        System.out.println("temp-13");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_SUPERVISE", lERPSolAllowSubmit);
        System.out.println("temp-14");

        this.lERPSolAllowUnSubmit = vo.first().getAttribute(5).toString();
        System.out.println("temp-15");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_UNSUPERVISE", lERPSolAllowUnSubmit);
        System.out.println("temp-16");
        this.lERPSolAllowPrint = vo.first().getAttribute(6).toString();
        System.out.println("temp-17");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_ERP_ALLOW_PRINT",lERPSolAllowPrint);
        System.out.println("temp-18");

        this.lERPSolHLevel = vo.first().getAttribute(7).toString();
        this.lERPSolProjectId = vo.first().getAttribute(8).toString();
        System.out.println("temp-19");
        ADFContext.getCurrent().getPageFlowScope().put("GLOB_H_LEVEL",lERPSolHLevel);
        System.out.println("temp-20");

        System.out.println(lERPSolModuleId +"lERPSolModuleId");
        System.out.println(lERPSolAllowAdd +"lERPSolAllowAdd");
        System.out.println(lERPSolAllowDelete +"lERPSolAllowDelete");
        System.out.println(lERPSolIsAllow +"lERPSolIsAllow");
        System.out.println(lERPSolAllowEdit +"lERPSolAllowEdit");
        System.out.println(lERPSolAllowSubmit +"lERPSolAllowSubmit");
        System.out.println(lERPSolAllowUnSubmit +"lERPSolAllowUnSubmit");
        System.out.println(lERPSolAllowPrint +"lERPSolAllowPrint");
        System.out.println(lERPSolHLevel +"lERPSolHLevel");
        
        System.out.println("-----------finish-------------------");         
        
        vo = am.findViewObject("QVOWCP");
        
        if (vo != null) {
            vo.remove();
        }
        
        vo=am.createViewObjectFromQueryStmt("QVOWCP", "select PARAMETER_VALUE FROM so_sales_parameter a where a.Parameter_Id='PAY_FORM_SERVER_URL'");
        vo.executeQuery();
        pERPUrl=vo.first().getAttribute(0).toString();
        vo.remove();
        vo=am.createViewObjectFromQueryStmt("QVOWCP", "select PATH PATH FROM SYSTEM a where a.PROJECTID='"+lERPSolProjectId+"' ");
        vo.executeQuery();
        String PFormURL=vo.first().getAttribute(0).toString()+"FORMS\\\\";
        System.out.println("PFormURL"+PFormURL);
        PFormURL=PFormURL+lERPSolActionRuntime;
        pERPUrl=pERPUrl.replace("<P_FORM_NAME>", PFormURL);
        pERPUrl=pERPUrl.replace("<P_USERID>", ERPSolGlobClassModel.doGetUserCode());
        System.out.println("pERPUrl"+pERPUrl);
        
    }


    public String doGotoERPSolActivity() {
    System.out.println("lErpActivityRuntime"+ "ERP-ACT-"+lERPSolActionRuntime);
    return "ERP-ACT-"+lERPSolActionRuntime;
    }


    public void setLERPSolUserCode(String lERPSolUserCode) {
        this.lERPSolUserCode = lERPSolUserCode;
    }

    public String getLERPSolUserCode() {
        
        try {
            return ERPSolGlobClassModel.doGetUserCode();
        } catch (NullPointerException e) {
            // TODO: Add catch code
            return "ORACLE";
        }
    }

    public void setLERPSolLocationCode(String lERPSolLocationCode) {
        this.lERPSolLocationCode = lERPSolLocationCode;
    }

    public String getLERPSolLocationCode() {
        return ERPSolGlobClassModel.doGetUserLocationCode();
    }

    public void setLERPSolStoreId(String lERPSolStoreId) {
        this.lERPSolStoreId = lERPSolStoreId;
    }

    public String getLERPSolStoreId() {
        return  ERPSolGlobClassModel.doGetUserStoreCode();
    }

    public void setLERPSolRegionId(String lERPSolRegionId) {
        this.lERPSolRegionId = lERPSolRegionId;
    }

    public String getLERPSolRegionId() {
        return  ERPSolGlobClassModel.doGetUserRegionCode();
    }
    public String doERPLogOutApp() {
        return "ACT-ERP-SOL-LOGOUT";
    }

    public void setLERPShowMenu(String lERPShowMenu) {
        this.lERPShowMenu = lERPShowMenu;
    }

    public String getLERPShowMenu() {
        return lERPShowMenu;
    }
    
    public void doERPSolSetMenuOff(){
        setLERPShowMenu("NO");
    }

    public void setLERPSolProjectId(String lERPSolProjectId) {
        this.lERPSolProjectId = lERPSolProjectId;
    }

    public String getLERPSolProjectId() {
        return lERPSolProjectId;
    }

    public void doErpSolOpenNewTab(String url) {
    ExtendedRenderKitService erks =
    Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
    StringBuilder strb = new StringBuilder("window.open('" + url + "');");
    erks.addScript(FacesContext.getCurrentInstance(), strb.toString());
    }
}
