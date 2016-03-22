package eru;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.conn.HttpHostConnectException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dyyr on 2015/4/26.
 */
public class test {

    private static Pattern p;
    private static Matcher m;
    private static String applyPageS, applicationId;
    private static HtmlPage rootPage, applyPage, editPage;
    private static WebClient webclient;



    private static int wormTest()throws Exception{
        try {


            webclient.getCurrentWindow().getEnclosedPage();
            rootPage = webclient.getPage("https://www.immigration.govt.nz/secure/Login+Working+Holiday.htm");
//            rootPage = webclient.getPage("https://www.immigration.govt.nz/");
//            System.out.println(rootPage.asXml());
            final HtmlInput username = (HtmlInput)rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_userNameTextBox");
            final HtmlInput password = (HtmlInput)rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_passwordTextBox");

//            username.setValueAttribute("tadarcy");
//            password.setValueAttribute("Zxc12345");

            username.setValueAttribute("dudeea");
            password.setValueAttribute("Dd123456");

//            username.setValueAttribute("hansha");
//            password.setValueAttribute("Dd123456");

            ((HtmlInput) rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_loginImageButton")
            ).click();
            rootPage.cleanUp();
            System.out.println("===================edit===================");
            applyPage = webclient.getPage("https://www.immigration.govt.nz/workingholiday/");
            HtmlAnchor editLink = (HtmlAnchor) applyPage.getElementById("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_editHyperLink");

            editPage = editLink.click();
//            webclient.waitForBackgroundJavaScript(10000);
            System.out.println("===========================click to get edit page : \n" + editPage.asXml());
            return 1;
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException){
                System.out.println("====================================catch the HttpHostConnectException!===============================================");
                return 0;
            }
            if (e instanceof SocketTimeoutException){
                System.out.println("====================================catch the SocketTimeoutException!===============================================");
                return 0;
            }

            if(e instanceof UnknownHostException){
                System.out.println("====================================catch the UnknownHostException!===============================================");
                return 0;
            }
            throw e;
        }
        finally {
            webclient.closeAllWindows();
        }
    }


    public void test1(){
//        System.out.println(Worm.class.getResource("jsp/personal2.jsp"));
//        System.out.println(Worm.class.getResource("jsp/personal2.jsp").getFile());

        String str = Worm.file2string("jsp/personal2.jsp");
        System.out.println("root: "+ Worm.root);
        System.out.println(str);
    }
    public static void main(String[] args) throws Exception {
//        if(args.length>0) {
//            Worm.root = args[0];
//            System.out.println("args0:" + args[0]);
//        }
//          test t33 = new test();
//          t33.test1();

//        WebClient webClient=new WebClient();
//        HtmlPage page = webClient.getPage("http://passport.tianya.cn/login.jsp"); // 填入用户名和密码
//        HtmlInput username = (HtmlInput) page.getElementById("userName");
//        username.type("ifugletest2014");
//        HtmlInput password = (HtmlInput) page.getElementById("password");
//        password.type("test123456"); // 提交
//        HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");
//        HtmlPage nextPage = submit.click();
//        System.out.println(nextPage.asXml());

//        test.getClassLoader().getResource("/").getPath();
//        System.out.println(Worm.class.getResource("/"));

//                float nan=0;
//        float nv=0;
//
//        float no=0;
//                for (float i=0; i<1000000;i++){
//                    nan+=1;
//                    for (float j=0; j<1000000;j++){
//                        nv=j;
//                        if((nan-8)/7==nv/5 && (nv-40)/5==nan/9){
//                            no+=1;
//                            System.out.println("nan:"+nan+", nv:"+nv + ", sum:"+(nan+nv)+", no="+no);
//                        }
//                    }
//                }


        Pattern pp = Pattern.compile("(?<=name=\"wc_t\" value=\").+(?=\" />)");

//        Pattern pp = Pattern.compile("(?<=name=\"wc_t\" value=\").+");
        String ss= "name=\"wc_t\" value=\"e0a3eb9d-2f0a-497f-8cb6-e4c942dc8d63\" />";
        Matcher mm = pp.matcher(ss);
        while (mm.find()) {
            System.out.println("==========================" + mm.group());
        }




        if(0==1){

        String str2 = "Content-Length=1410, Content-Type=application/x-javascript, Expires=30 Oct 1999 14:19:41 GMT, Last-Modified=Tue, 13 May 2014 03:54:54 GMT, Accept-Ranges=bytes, ETag=\"07b27195f6ecf1:53b3\", X-Powered-By=ASP.NET, Date=Sun, 10 May 2015 07:33:28 GMT, Set-Cookie=TS0120d49b=0152807fb296341d397cf9942aee33077a16699c9a1519f7209fc9b9ab3c6fabe2584edee6d0c365958d9797d54a75c9b316d8e3e691b5676b031001defc0cf11b2e1024be8cfc542f781741888ca5a69a3bb78af51116ac799947f89740a01603424ca85bf6b524db9ac20097a64da897b0b635e87da733a8900b66f24398c4f85f90b6b0; Path=/";
        String[] strs = str2.split(", (?=[^,]*=)");
        for (String s : strs) {
            String[] t = s.split("=",2);
            System.out.println("t0:"+t[0]);
            System.out.println("t1:"+t[1]);
        }


//        File file = new File(test.class.getResource("/js/realme.js").getFile());
//        BufferedReader reader = null;
//        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            String jsFile = null;
//            int line = 1;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
//                line++;
//                jsFile += tempString;
//            }
//            reader.close();
//            System.out.println(jsFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
            String s1="\n" +
                    "\n" +
                    "(function(){\n" +
                    "    var securemsg;\n" +
                    "    var packmsg;\n" +
                    "    var CryptoUtils;\n" +
                    "\n" +
                    "try{(function(){try{var _S,IS,lS=1,LS=1,ZS=1,__=1,i_=1,I_=1,j_=1,J_=1;for(var l_=0;l_<IS;++l_)lS+=2,LS+=2,ZS+=2,__+=2,i_+=2,I_+=2,j_+=2,J_+=3;_S=lS+LS+ZS+__+i_+I_+j_+J_;window.JS===_S&&(window.JS=++_S)}catch(L_){window.JS=_S}var O_=window.sdkljshr489=!0;function z_(S){window.sdkljshr489&&S&&(O_=!1);return O_}function Z_(){}z_(window[Z_.name]===Z_);z_(\"undefined\"===window.vodsS0);window.vodsS0=null;z_(/\\x3c/.test(function(){return\"\\x3c\"})&/x3d/.test(function(){return\"0\";\"x3d\"}));\n" +
                    "var s_=/mobi/i.test(navigator.userAgent),Si=+new Date,_i=s_?3E4:3E3;function ii(){return z_(Si+_i<(Si=+new Date))}\n" +
                    "(function(){var S=-1,S={i:++S,OS:\"false\"[S],S:++S,s:\"false\"[S],j:++S,o_:\"[object Object]\"[S],IS:(S[S]+\"\")[S],SS:++S,iS:\"true\"[S],I:++S,J:++S,zS:\"[object Object]\"[S],_:++S,l:++S,lI:++S,JI:++S};try{S.Z=(S.Z=S+\"\")[S.J]+(S.o=S.Z[S.S])+(S.oS=(S.L+\"\")[S.S])+(!S+\"\")[S.SS]+(S.O=S.Z[S._])+(S.L=\"true\"[S.S])+(S.jS=\"true\"[S.j])+S.Z[S.J]+S.O+S.o+S.L,S.oS=S.L+\"true\"[S.SS]+S.O+S.jS+S.L+S.oS,S.L=S.i[S.Z][S.Z],S.L(S.L(S.oS+'\"\\\\'+S.S+S.J+S.S+S.OS+\"\\\\\"+S.I+S.i+\"(\"+S.O+\"\\\\\"+S.S+S.l+S.S+\"\\\\\"+S.S+S._+S.i+S.iS+S.o+S.OS+\n" +
                    "\"\\\\\"+S.I+S.i+\"\\\\\"+S.S+S._+S.l+\"\\\\\"+S.S+S.J+S.S+\"\\\\\"+S.S+S.J+S._+S.IS+S.o+\"\\\\\"+S.S+S._+S.l+\"['\\\\\"+S.S+S._+S.i+S.s+\"\\\\\"+S.S+S.l+S.S+\"false\"[S.j]+S.o+S.s+S.IS+\"']\\\\\"+S.I+S.i+\"===\\\\\"+S.I+S.i+\"'\\\\\"+S.S+S._+S.SS+S.O+\"\\\\\"+S.S+S._+S.j+\"\\\\\"+S.S+S.J+S.S+\"\\\\\"+S.S+S.J+S._+\"\\\\\"+S.S+S.I+S.l+\"')\\\\\"+S.I+S.i+\"{\\\\\"+S.S+S.j+\"\\\\\"+S.S+S.S+\"\\\\\"+S.S+S._+S._+S.s+\"\\\\\"+S.S+S._+S.j+\"\\\\\"+S.I+S.i+S.iS+S.IS+\"\\\\\"+S.S+S._+S._+S.zS+\"\\\\\"+S.S+S.l+S.S+S.jS+\"\\\\\"+S.S+S.J+S.j+\"\\\\\"+S.S+S.J+S.SS+\"\\\\\"+S.S+S._+S.i+\"\\\\\"+S.I+S.i+\"=\\\\\"+S.I+S.i+\n" +
                    "\"\\\\\"+S.S+S._+S.l+\"\\\\\"+S.S+S.J+S.S+\"\\\\\"+S.S+S.J+S._+S.IS+S.o+\"\\\\\"+S.S+S._+S.l+\"['\\\\\"+S.S+S._+S.i+S.s+\"\\\\\"+S.S+S.l+S.S+\"false\"[S.j]+S.o+S.s+S.IS+\"'].\\\\\"+S.S+S._+S.j+S.iS+\"\\\\\"+S.S+S._+S.i+\"false\"[S.j]+S.s+S.zS+S.iS+\"(/.{\"+S.S+\",\"+S.I+\"}/\\\\\"+S.S+S.I+S.l+\",\\\\\"+S.I+S.i+S.OS+S.jS+\"\\\\\"+S.S+S.J+S._+S.zS+S.O+\"\\\\\"+S.S+S.J+S.S+S.o+\"\\\\\"+S.S+S.J+S._+\"\\\\\"+S.I+S.i+\"(\\\\\"+S.S+S.l+S.i+\")\\\\\"+S.I+S.i+\"{\\\\\"+S.S+S.j+\"\\\\\"+S.S+S.S+\"\\\\\"+S.S+S.S+\"\\\\\"+S.S+S.S+\"\\\\\"+S.S+S._+S.j+S.iS+S.O+S.jS+\"\\\\\"+S.S+S._+S.j+\"\\\\\"+S.S+S.J+S._+\n" +
                    "\"\\\\\"+S.I+S.i+\"(\\\\\"+S.S+S.l+S.i+\"\\\\\"+S.I+S.i+\"+\\\\\"+S.I+S.i+\"\\\\\"+S.S+S.l+S.i+\").\\\\\"+S.S+S._+S.SS+S.jS+S.o_+\"\\\\\"+S.S+S._+S.SS+S.O+\"\\\\\"+S.S+S._+S.j+\"(\"+S.j+\",\\\\\"+S.I+S.i+S.I+\")\\\\\"+S.S+S.j+\"\\\\\"+S.S+S.S+\"\\\\\"+S.S+S.S+\"});\\\\\"+S.S+S.j+\"}\\\\\"+S.S+S.j+'\"')())()}catch(J){S%=5}})();window.zI={jj:\"083cc4fa1583400130746311bd0266c6f65177ca83f9f8109df20812f578bd78a4fafc18d3875dd6621d061977c2a573ac6c65bb69c5a70422e6411d3090fb674f22e5babe585d113162ffdfa69fbdfeb1cf3f9afb18d8777e92d3ca7ca93d7d38aa7202776e5f19fbd81045c7f54b8c49212d3bb54ccfdbd6de9c8b4b6ddb8edb419c8ea3b94bf83ed84cac0c43cd1daddcc52904b1a57a8f6160095b897c62b981004654ffe883896429c2de397cb8555f4ac1bc7580ec249b53f139d70ffca37da2f721daa809d9b3847bb6ac741c79068f023c73cb9a64085bc8ffb62eac5ae857e10555d0acbf2edee248933aa41342ed63450d95ad7f15116e184494154c09d7e56e943fa8c7f6316b992a8300071686747aede5345bd354900c49e4f59e1b84db3128314ae653c0e094548f8c60eedf2fcbefc20aa6faf6437a253dfe4f139b61dfd39eea\"};function _(S){return 571>S}function I(){var S=arguments.length;for(var J=0;J<S;++J)arguments[J]-=46;return String.fromCharCode.apply(String,arguments)}function L(S){return S.toString(36)}\n" +
                    "(function(S){S||setTimeout(function(){var S=setTimeout(function(){},250);for(var l=0;l<=S;++l)clearTimeout(l)},500)})(ii());var b;})();}finally{sdkljshr489=false;ie9rgb4=void(0);};\n" +
                    "eval((ie9rgb4=function (){var m='function () {/*fQb f_TcC}-di`U_V YU)bWR$+dbikuVe^SdY_^uvkdbikfQb OCy9Cy\\\\C-!y<C-!yJC-!yOO-!+V_bufQb \\\\O-}+\\\\O,9C+xx\\\\Ov\\\\Cx-\"y<Cx-\"yJCx-\"yOOx-#+OC-\\\\Cx<CxJCxOO+gY^T_g{:C---OCssugY^T_g{:C-xxOCvmSQdSXu<OvkgY^T_g{:C-OCmfQb ?O-gY^T_g{cT[\\\\ZcXb$()-n}+Ve^SdY_^ jOuCvkgY^T_g{cT[\\\\ZcXb$()ssCssu?O-n!v+bUdeb^ ?OmVe^SdY_^ JOuvkmjOugY^T_gKJO{^Q]UM---JOv+jOuoe^TUVY^UTo---gY^T_g{f_TcC}v+gY^T_g{f_TcC}-^e\\\\\\\\+jOu|Lh#S|{dUcduVe^SdY_^uvkbUdeb^oLh#Somvs|h#T|{dUcduVe^SdY_^uvkbUdeb^o}o+oh#Tomvv+\\r\\nfQb cO-|]_RY|Y{dUcdu^QfYWQd_b{ecUb1WU^dvyCY-x^Ug 4QdUyOY-cO/#5$*#5#+Ve^SdY_^ YYuvkbUdeb^ jOuCYxOY,uCY-x^Ug 4QdUvvmuVe^SdY_^uvk#rYYuvssUfQ\\\\uVe^SdY_^uCvkC-C{c`\\\\Yduo\\\\ov+fQb :-oo+V_bufQb \\\\-}+\\\\,C{\\\\U^WdX+xx\\\\v:x-CdbY^W{Vb_]3XQb3_TUuCK\\\\Mv+bUdeb^ :muo$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\$!\\\\!\"#\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\&!\\\\!}}\\\\!!!\\\\))\\\\!!\\'\\\\!})\\\\!}!\\\\!!}\\\\!!&\\\\$&\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\)\\'\\\\!}(\\\\!}(\\\\&!\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\)\\'\\\\!!$\\\\!!$\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\!!$\\\\!!$\\\\$&\\\\!}\"\\\\!}%\\\\!}(\\\\!!&\\\\!}!\\\\!!$\\\\$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\$\\'\\\\!}%\\\\!!%\\\\$\\'\\\\$&\\\\!!&\\\\!}!\\\\!!%\\\\!!&\\\\$}\\\\!\"}\\\\$!\\\\!\"%\\\\$!\\\\$&\\\\!}(\\\\!}!\\\\!!}\\\\!}#\\\\!!&\\\\!}$\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!\\'\\\\&!\\\\#)\\\\($\\\\!}!\\\\!\"}\\\\!!&\\\\#\"\\\\!}%\\\\!!%\\\\#\"\\\\)\\'\\\\!!(\\\\)\\'\\\\!}%\\\\!}(\\\\)\\'\\\\)(\\\\!}(\\\\!}!\\\\#\"\\\\!!\\'\\\\!!}\\\\!}}\\\\!}!\\\\!!$\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\#\"\\\\&\\'\\\\!!$\\\\!}!\\\\)\\'\\\\!!&\\\\!}%\\\\!!(\\\\!}!\\\\#\"\\\\&\\'\\\\!!!\\\\!})\\\\!})\\\\!!!\\\\!!}\\\\!!%\\\\#\"\\\\&%\\\\!!&\\\\!!&\\\\!!$\\\\!}%\\\\)(\\\\!!\\'\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$%\\\\(#\\\\!}$\\\\)\\'\\\\!!$\\\\!}!\\\\&%\\\\!}(\\\\!}%\\\\!}\\'\\\\!}!\\\\#\"\\\\\\'&\\\\!}%\\\\))\\\\!}!\\\\!!}\\\\!!%\\\\!}!\\\\%)\\\\)\\'\\\\!}}\\\\!}}\\\\!}%\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\)\\'\\\\!}(\\\\#\"\\\\!!&\\\\!}!\\\\!!$\\\\!})\\\\!!%\\\\#\"\\\\!})\\\\)\\'\\\\!\"!\\\\#\"\\\\)\\'\\\\!!\"\\\\!!\"\\\\!}(\\\\!\"!\\\\$&\\\\#\"\\\\&&\\\\!\"!\\\\#\"\\\\!!\\'\\\\!!%\\\\!}%\\\\!!}\\\\!}#\\\\#\"\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\#\"\\\\!!%\\\\!}%\\\\!!&\\\\!}!\\\\$$\\\\!\"!\\\\!!!\\\\!!\\'\\\\#\"\\\\)\\'\\\\!}#\\\\!!$\\\\!}!\\\\!}!\\\\#\"\\\\!!&\\\\!!!\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\#\"\\\\($\\\\!}!\\\\!!$\\\\!})\\\\!!%\\\\#\"\\\\!!!\\\\!}\"\\\\#\"\\\\(%\\\\!!%\\\\!}!\\\\#\"\\\\)\\'\\\\!!}\\\\!}}\\\\#\"\\\\(}\\\\!!$\\\\!}%\\\\!!(\\\\)\\'\\\\))\\\\!\"!\\\\#\"\\\\(}\\\\!!!\\\\!}(\\\\!}%\\\\))\\\\!\"!\\\\#)\\\\$&\\\\!!%\\\\!!\"\\\\!}(\\\\!}%\\\\!!&\\\\$}\\\\$\\'\\\\(\\'\\\\$#\\\\$\\'\\\\$!\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$}\\\\)\\'\\\\!!$\\\\!!$\\\\$!\\\\!\"#\\\\!!\\'\\\\$&\\\\!!\"\\\\!!!\\\\!!\"\\\\$}\\\\$!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\!!$\\\\!!$\\\\%)\\\\!\"%\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!$\\\\&!\\\\!\"#\\\\!}(\\\\!!!\\\\!!%\\\\!}!\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\$!\\\\!\"#\\\\!!\\'\\\\&!\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$}\\\\!!\\'\\\\$!\\\\!\"%\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!\\'\\\\))\\\\)\\'\\\\!!}\\\\&!\\\\!\"#\\\\!}\\'\\\\!}!\\\\!}!\\\\!!\"\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\$&\\\\))\\\\)\\'\\\\))\\\\!}$\\\\!}!\\\\$&\\\\!!\"\\\\!!\\'\\\\!!%\\\\!}$\\\\$}\\\\!\"}\\\\$!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!!&\\\\!!$\\\\!!\\'\\\\!}!\\\\%)\\\\!\"%\\\\$$\\\\!!&\\\\!!$\\\\!!\\'\\\\!!%\\\\!!&\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!\"}\\\\&!\\\\&!\\\\&!\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\!\"%\\\\$$\\\\))\\\\)\\'\\\\))\\\\!}$\\\\!}!\\\\%(\\\\)!\\\\)#\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!}}\\\\!!!\\\\!!\\'\\\\)(\\\\!!&\\\\&!\\\\%!\\\\$(\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\\\'#\\\\($\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!}\"\\\\!!!\\\\!!$\\\\$}\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!\"!\\\\#\"\\\\!}%\\\\!!}\\\\#\"\\\\!\"}\\\\$!\\\\!}%\\\\!}\"\\\\$}\\\\!\"}\\\\)!\\\\!\"!\\\\)#\\\\&!\\\\&!\\\\&!\\\\%$\\\\%$\\\\%%\\\\$!\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!!&\\\\!!$\\\\!!\\'\\\\!}!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!}\"\\\\)\\'\\\\!}(\\\\!!%\\\\!}!\\\\!\"%\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\)(\\\\!}(\\\\)\\'\\\\!})\\\\!}!\\\\$}\\\\)\\'\\\\$$\\\\)(\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\$}\\\\)(\\\\$!\\\\!\"%\\\\%)\\\\!}%\\\\!}\"\\\\$}\\\\!!\\'\\\\))\\\\)\\'\\\\!!}\\\\$&\\\\!}\\'\\\\!}!\\\\!}!\\\\!!\"\\\\$}\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\$!\\\\$!\\\\!\"#\\\\!!)\\\\!}$\\\\!}%\\\\!}(\\\\!}!\\\\$}\\\\)\\'\\\\!}(\\\\!}(\\\\$}\\\\!!\\'\\\\$!\\\\$!\\\\!\"#\\\\!!$\\\\$&\\\\!}(\\\\!!!\\\\!!%\\\\!}!\\\\$}\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$!\\\\#(\\\\#(\\\\)(\\\\!}(\\\\)\\'\\\\!})\\\\!}!\\\\$}\\\\\\'#\\\\($\\\\$$\\\\!!\\'\\\\$!\\\\%)\\\\!\"%\\\\!\"%\\\\!\"%\\\\$!\\\\$}\\\\$!ovvmvuv+\\r\\ncUSebU]cW-kO\\\\C*Ve^SdY_^uCvkbUdeb^ cUSebU]cWK<u\"(()\\'vMucUSebU]cW{c9uuOu\"}%vy}v\n" +
                    "yCyOu($)v/}*!vyVe^SdY_^uvkbUdeb^ CdbY^WK9u!$(y!&}y!%\\'y!%%y!!#y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'vMu=QdXK<u\"&\"}&}!!vMu=QdXK<u!&%}$\\'#\\'#$vMuvwuOu)&!v/#(}*\"%&vxuOu#}!v/!*}vvruOu&(!v/\"!!*\"%&vvmvK<u)!(\"#)vMuoovmyjO*Ve^SdY_^uCvkbUdeb^uuCsuOu(&(v/#\\'$*\"%%vv,,uOu!\\'$v/\"$*!&vluCsuOu##)v/&%\"(}*$(#\\'%vv,,uOu%&&vy(vlC..uOu\"#%v/(*$vsuOu)(#v/(}#\"\"*&%\"(}vlC..uOu\\'%#v/!)*\"$vsuOu!}#v/\"%%*\"%\"vv...uOu\\'#}vy}vmy:%*Ve^SdY_^uCy:vkV_bufQb \\\\-ooy?-uOu%(!vy}v+?,\\r\\nCK<u!\")$#))\"}%vM+?xxv\\\\x-CdbY^WKoLe}}&&b_Lh&T3XLe}}&!b3Lh&VTUoMuCK9u!$%y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMuu?xCK<u!\")$#))\"}%vMz:vrCK<u!\")$#))\"}%vMvv+bUdeb^ \\\\myZ:C*Ve^SdY_^uCy:vkbUdeb^ cUSebU]cW{:%uCyCK<u!\")$#))\"}%vMz:vmy<Y*Ve^SdY_^uCy:vkYVuCK<u!\")$#))\"}%vMn-:K<u!\")$#))\"}%vMvdXb_g cUSebU]cW{ZOuCvycUSebU]cW{ZOu:vyoo+V_bufQb \\\\-ooy?-uOu\"!vy}v+?,CK<u!\")$#))\"}%vM+?xxv\\\\x-CdbY^WKoLe}}&&b_Lh&T3XLe}}&!b3Lh&VTUoMuCK9u!$%y\\r\\n!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMu?vN:KoLe}}&#XQb3Lh&VTU1doMu?vv+bUdeb^ \\\\my\\\\O*Ve^SdY_^uCy:vkbUdeb^uuC...uOu\\'%$vy}vvxu:...uOu(&\"vy}vvsuOu(#$v/\"!$\\'$(#&$\\'*$\")$)&\\'\")%vv...uOu($}vy}vmy9\\\\*Ve^SdY_^uCy:vkbUdeb^uuC...uOu#)vy}vvz:suOu)}\"v/\"!$\\'$(#&$\\'*$\")$)&\\'\")%vv...uOu)\\'&vy}vmyj\"*Ve^SdY_^uCy:y\\\\vkdbikYVuCK<u!\")$#))\"}%vMn-uOu%%(v/!&*!!vvdXb_goo+YVu:K<u!\")$#))\"}%vMn-uOu&(%v/!}*(vvdXb_goo+fQb ?-cUSebU]cW{OjuCv+?KOu&(vy}M-cUSebU]cW{jOu?KOu$&)vy}Mv+?KOu%)&v/}*!M-cUSebU]cW{jOu?KOu(\"%v/\\r\\n}*!Mv+?KOu\"##v/\"*!M-cUSebU]cW{jOu?KOu&)!v/!*\"Mv+?KOu#(\\'v/#*!M-cUSebU]cW{jOu?KOu%$!v/#*\"Mv+fQb j-cUSebU]cW{Oju:vyJ-cUSebU]cW{jOujKOu(#vy}Mvyc-cUSebU]cW{jOujKOu%$\\'v/!*}MvyCC-u\\\\/Ou\"}$v/$\"$\\'})\\'\"#}$*\"!$\\'$(#&$\\'*uOu$}(vy}vv...uOu\\'#vy}v+YVu\\\\vV_bufQb YC-Ou)&\\'v/!\\'*!%+YC.-uOu(#(vy}v+YCzzvfQb ZC-cUSebU]cW{\\\\OuJ,,uOu&\"(v/#*$vNJ...uOu$\\'!v/%*\"vyJvy?C-cUSebU]cW{\\\\OuCCy?KCC...uOu())v/!%*!!vsuOu!&\\'vy#vMvyc-cUSebU]cW{9\\\\ucyZCN?CvyCC-cUSebU]cW{9\\\\uCCyOu\"\\'$v/\"&%$$#%\\'&)*\"!$\\'$(#&$\\'vyCO-cUSebU]cW{\\\\Ouc,,uOu)\"\"v/%*$vNc...uOu\"()v/\\r\\n%*&vycvycC-cUSebU]cW{\\\\OuCCy?KCCsuOu%!!vy#vMvyJ-cUSebU]cW{9\\\\uJyCONcCv+U\\\\cU V_buCC-uOu!!#vy}vyYC-uOu%$\"vy}v+YC,uOu#$\"vy!&v+YCxxvZC-cUSebU]cW{\\\\Ouc,,uOu!!}v/$*%vNc...uOu&\"$vy%vycvy?C-cUSebU]cW{\\\\OuCCy?KCCsuOu#\")v/#*!vMvyJ-cUSebU]cW{\\\\OuJyZCN?CvyCC-cUSebU]cW{\\\\OuCCyOu#\"}v/\"&%$$#%\\'&)*!$\\'\\'\\')!)}#vyCO-cUSebU]cW{\\\\OuJ,,uOu\"#}v/$*\"vNJ...uOu(%#vy%vyJvycC-cUSebU]cW{\\\\OuCCy?KCC...uOu$##v/!!*\\'vsuOu%)}v/\"*#vMvyc-cUSebU]cW{\\\\OucyCONcCv+J-cUSebU]cW{jOuJv+c-cUSebU]cW{jOucv+bUdeb^ cUSebU]cW{cquKJycMvmSQdSXu_OvkdXb_g _O+mmy\\r\\n_:*Ve^SdY_^uCy:y\\\\vkbUdeb^ cUSebU]cW{j\"uCy:y\\\\vmyJ:*Ve^SdY_^uCy:vkV_bufQb \\\\-ooy?-uOu&&\\'vy}v+?,:+?xxv\\\\x-C+bUdeb^ \\\\myJ?*Ve^SdY_^uCy:y\\\\vk:-:zCK<u!\")$#))\"}%vMr:zuOu\"}!v/!*}v+V_bufQb ?-ooyj-uOu!!}vy}v+j,:+jxxv?x-\\\\+bUdeb^ Cx?xCdbY^WK9u!$(y!&}y!%\\'y!%%y!!#y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'vMu:vmyCCC*Ve^SdY_^uCvkbUdeb^ CK<u$(}#\"\\')(vMuuOu(#}vy}vyCK<u!\")$#))\"}%vMzCKoLe}}&#XQLh\\'\"3_Le}}&$U1doMuCK<u!\")$#))\"}%vMzuOu!$v/!*}vvzuOu()v/!*}vvmy\\\\?*Ve^SdY_^uCy:y\\\\vkfQb ?-9u$&y$&y$&y\\r\\n$&y$&y$&y$&y$&vyj-oo+YVu\\\\vkYVu:K<u!\")$#))\"}%vMruOu%}$v/(*\\'vn-uOu!()vy}vvdXb_goo+\\\\-:K<u!\")$#))\"}%vM|uOu!}\\'v/(*)v+V_bufQb J-uOu%$\"vy}v+J,\\\\+JxxvfQb c-:K<u!\\'$#))!)(#vMuJwuOu\\'(}v/\\'*(vyOu\\')$v/!!*(vyj-jxcUSebU]cW{<YucUSebU]cW{_:uCycyYYuvvy?vy?-c+bUdeb^ cUSebU]cW{CCCujvm:-cUSebU]cW{J?u:yOu%$}v/(*%yoLhVVov+\\\\-:K<u!\")$#))\"}%vM|uOu&!(v/%*(v+V_buJ-uOu$&&vy}v+J,\\\\+Jxxvc-:K<u!\\'$#))!)(#vMuJwuOu$%!v/(*&vyOu($(v/$*(vy?-cUSebU]cW{_:uCycUSebU]cW{<Yu?ycvyn!vyjx-?+bUdeb^ jmy?:*Ve^SdY_^uCvkfQb :-<u\"}!\"$(\\'\"(\")(&v+\\r\\nC-cUSebU]cW{J?uCyOu)\"(v/$*(y<u#$vv+V_bufQb \\\\-CK<u!\")$#))\"}%vM|uOu!$}v/(*!!vy?-uOu\"\"%vy}v+?,\\\\+?xxvfQb j-CK<u!\\'$#))!)(#vMu?wuOu()v/(*)vyOu\"}&v/(*!!vyj-jxcUSebU]cW{<Yujy9u\"\")y\"&#y\\'(y%)y!}\\'y\"$$y!%$y!!)vvy:-cUSebU]cW{<Yu:ycUSebU]cW{_:ujy:yn!vv+bUdeb^ :myZ?*Ve^SdY_^uCy:vkfQb \\\\-CK<u!\")$#))\"}%vM,-uOu&%)v/!%*!&v/C*cUSebU]cW{?:uCv+\\\\K<u!\")$#))\"}%vM,uOu(\\'$v/\"!*!&vssu\\\\x-cUSebU]cW{J:uoLh}}oyuOu%&!v/!&*!)vz\\\\K<u!\")$#))\"}%vMvv+fQb ?-cUSebU]cW{<Yu\\\\ycUSebU]cW{J:u9u!#(vyOu(!&v/\"!*!&vvy\\\\-cUSebU]cW{<Yu\\\\ycUSebU]cW{J:u<u&vy\\r\\nOu&\\'v/!&*\"}vv+bUdeb^ cUSebU]cW{?:u?xcUSebU]cW{?:u\\\\x:vvmyJYC*uOu)!\\'vy(vyZO*Ve^SdY_^uCvkbUdeb^ cUSebU]cWK<u\"(()\\'vMucUSebU]cW{c9uuOu)!$vy}vyCK<u!\")$#))\"}%vMyOu%$$v/!*}vyVe^S\n" +
                    "dY_^u:vk:->e]RUbuCKoLe}}&#XQb3Lh&VTU1doMu:vvK9u!&\"y!%\\'y!\")y!&\"y!&}y!%!y!%&y!$)vMuOu\"#\"v/!&*!$v+bUdeb^ :K<u!\")$#))\"}%vM--uOu$\"$v/!*}v/oLe}}#}ox:*:mvK<u)!(\"#)vMuoovmyj:*Ve^SdY_^uCvkbUdeb^ cUSebU]cWK<u\"(()\\'vMucUSebU]cW{c9uuOu%!#vy}vyCK<u!\")$#))\"}%vMyOu\\'$$v/!*\"vyVe^SdY_^u:vkbUdeb^ CdbY^WK9u!$(y\\r\\n!&}y!%\\'y!%%y!!#y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'vMu>e]RUbuoLh#}Le}}\\'(oxCK<u!\\'$#))!)(#vMu:yOu(&&v/!*\"vvvmvK<u)!(\"#)vMuoovmyc9*Ve^SdY_^uCy:y\\\\vkYVu\\\\,-uOu&\"$vy}vvdXb_goo+V_bufQb ?-KM+C,:+Cx-\\\\v?K<u!\"}&#}%vMuCv+bUdeb^ ?my\\\\%*Ve^SdY_^uCy:y\\\\vkYVu\\\\.-uOu$&%vy}vvdXb_goo+V_bufQb ?-KM+C.:+Cx-\\\\v?K<u!\"}&#}%vMuCv+bUdeb^ ?my:\"*Ve^SdY_^uCvkbUdeb^ CsuOu)()v/!$}*\"%%vmy?q*Ve^SdY_^uCvkYVuCK<u!\")$#))\"}%vM.uOu%\\'\"v/%*$vvdXb_goo+V_bufQb :-uOu(!%vy}vy\\\\-uOu$)\\'vy}v+\\\\,CK<u!\")$#))\"}%vM+\\\\xxv:-u:,,uOu\"(\"v/\\r\\n(*)vvxCK9u!$%y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMu\\\\v+bUdeb^ :...uOu#($vy}vmyJq*Ve^SdY_^uCy:vkYVuC,uOu!\"\"vy}vvdXb_goo+di`U_V :--<u(&$&$($#\\'%)})#vssu:-uOu&$$vy$vv+bUdeb^ cUSebU]cWK<u\"(()\\'vMucUSebU]cW{\\\\%u:zuOu\\'(&v/}*!vyuOu((\\'vyz!vyuOu$$#vyz!vvyVe^SdY_^u:vkbUdeb^ CdbY^WKoLh&&b_]Le}}$#XQbLh$#_TUoMucUSebU]cW{:\"uC..uOu&\"\"v/\\'*(vw:vvmvK<u)!(\"#)vMuoovmyOj*Ve^SdY_^uCvkV_bufQb :-KMy\\\\-uOu!!&vy}v+\\\\,CK<u!\")$#))\"}%vM+\\\\x-Ou\\'$$v/\"*$v:K<u!\"}&#}%vMucUSebU]cW{?quCK<u!\\'$#))!)(#vMu\\\\y\\r\\nOu&&}v/#*$vvv+bUdeb^ :mycq*Ve^SdY_^uCvkbUdeb^ cUSebU]cWK<u\"(()\\'vMucUSebU]cW{c9uuOu#%&vy}vyCK<u!\")$#))\"}%vMyOu\\')%v/}*!vyVe^SdY_^u:vkbUdeb^ cUSebU]cW{JquCK:MyuOu\"#$vy$vvmvK<u)!(\"#)vMuoovmyJc*Ve^SdY_^uCvkV_bufQb :-ooy\\\\-uOu))!vy}v+\\\\,CK<u!\")$#))\"}%vM+xx\\\\v:-u9u)$vxCKoLe}}&#XLh&!bLe}}$#_Lh&$ULe}}$!doMu\\\\vK9u!&\"y!%\\'y!\")y!&\"y!&}y!%!y!%&y!$)vMuOu%)&v/)*!&vvK<u$(}#\"\\')(vMuOu)!!v/z!*z\"vx:+bUdeb^ `QbcU9^du:yOu(#$v/!(*!&vmyYc*Ve^SdY_^uCy:vkV_bufQb \\\\-\\r\\nooy?-oLh#}oxCK9u!&\"y!%\\'y!\")y!&\"y!&}y!%!y!%&y!$)vMuOu\"!$v/!&*!}vyj-?K<u!\")$#))\"}%vM+j.uOu\"!vy}v+jz-Ou)\"&v/!*\"v\\\\x-CdbY^WKoLe}}&&Lh\\'\"Le}}&VLh&TLe}}$#Lh&(Le}}&!Lh\\'\"Le}}$#Lh&VLe}}&$Lh&%oMu`QbcU9^du?K<u$(}#\"\\')(vMujzuOu#!}vy\"vyjvyOu\"\\'$v/!&*!%vv+:-:ll\\\\K<u!\")$#))\"}%vM+\\\\x-1bbQiuuOu\")!v/!*}vx:z\\\\K<u!\")$#))\"}%vMvK<u)!(\"#)vMu9u$&vv+\\r\\nYVu\\\\K<u!\")$#))\"}%vMn--:vdXb_goo+bUdeb^ \\\\my9Z*oLe}}$!Lh$\"oy?\\\\*^e\\\\\\\\yc<*Ve^SdY_^uCy:vkbUdeb^ cUSebU]cW{\\\\Zuv{c<uCy:vmyZ9*Ve^SdY_^uCy:y\\\\y?yjvkbUdeb^ cUSebU]cW{\\\\Zuv{Z9uCy:y\\\\y?yjvmycUQ\\\\*Ve^SdY_^uCy:vkfQb \\\\-cUSebU]cW{\\\\Zuv{c<uCy:v+bUdeb^n!---\\\\/n!*cUSebU]cW{ZOu\\\\vmy?O*Ve^SdY_^uCy:y\\\\y?yjvkC-cUSebU]cW{j:uCv+:-cUSebU]cW{\\\\Zuv{Z9uCy:y\\\\y?yjv+di`U_V :--<u!$\\'}%&)}&)vssu:K<u!$\\'\\'!!)!\"%vMssu:K<u!$\\'\\'!!)!\"%vM-:K<u!$\\'\\'!!)!\"%vMwuOu#(%vy\"vvy:{9Ossu:{9Ow-uOu#)$vy\"vvv+bUdeb^ :my\\\\Z*Ve^SdY_^uvkYVuncUSebU]cW{?\\\\vkfQb Cy\\r\\n:-cUSebU]cW{YcuOu\"#}v/(*%yOu&\"(v/}*!vy\\\\-cUSebU]cW{JYCy?-uVe^SdY_^uvkVe^SdY_^ T!^uQyRvkbUdeb^ tOtK}M/QKRM*Q{SXQb1duRvm+bUdeb^uR-~KMyR-kOOO*xxRyqqqq*T!^uunKMxoovyRvyOOq*xxRyqOqO*T!^uunKMxoovyRvyOqO*xxRyqOqq*T!^uukmxoovyRvyqqOq*T!^uuRKRMxoovyRvyOqq*xxRyqqqO*T!^uunooxoovyRvyqOO*xxRyqOq*xxRyqqOO*T!^uukmxoovyRvyqqO*xxRyqqq*xxRyqOOO*xxRyqOOq*xxRmyR{qO-T!^uuR{qO-RxoovyR{qOqvxuR{Oq-T!^uR{qOyR{OOqvvxuR{qq-T!^uuR{qxoovyR{OOqvvxT!^uuunRvxoovyR{OqqvxuR{OO-T!^uR{qOyR{qqOvvxuR{q-T!^uunooxoovyR{OOqvvxuR{O-T!^uunooxoovyR{OqOvvxT!^uR{qOyR{qOqvxR{OOxR{OqxR{qyR{qq-R{qxT!^uunooxoovyR{OqqvxR{OOxR{OxR{qxR{qqyR{q-R{OOOKR{qOMKR{qOMyR{quR{qqxoLooxoLLoxR{Oxo}}RVoxoLLox\"$xoLoxLooxoLLox!\"xoLoxLooxoLLox!#xoLoxLooxoLLoxR{Oxo}})RoxoLLoxR{Oxo}}U$oxoLLoxR{Oxo}}V#oxoLLoxR{Oxo}}(VoxoLLox\"!xoLoxLooxoLLoxR{Oxo}}T#oxoLLox!%#xoLoxLooxoLLoxR{Oxo}}R!oxoLLox#\\'xoLoxLooxosLLox!!\"xoLoxLooxoLLox!%#xoLoxLooxoLoovuvvmvuv+?K<u!\")$#))\"}%vMn--uOu%($v/!)*!&vssu?-?K<u$(}#\"\\')(vMuuOu\\'#}vy}vyOu%%v/!&*)vv+cUSebU]cW{?\\\\-kc<*Ve^SdY_^u\\\\yJvkdbikYVuC--f_YTuOu#\"(vy}vvdXb_goo+YVuJK<u!\")$#))\"}%vMn-uOu\\'$)vy\"vvdXb_goo+fQb c-cUSebU]cW{j:uJvyCC-cUSebU]cW{\\\\?u?y\\\\yn!vyYC-cUSebU]cW{Z?u?yCCxCxcvxCCyZC-cUSebU]cW{YcuYCK<u!\")$#))\"}%vMyuOu%(!vy\"vv+bUdeb^ YC-:xCxcxZCxYCmSQdSXu?CvkbUdeb^n!mmyZ9*Ve^SdY_^u:yJycyCCyYCvkdbikYVuJK<u!\")$#))\"}%vMn-uOu$)!vy\"vvdXb_goo+fQb ZC-cUSebU]cW{j:uJv+\\r\\nYVu:K<u!\")$#))\"}%vM,uOu#&$v/(*\\'vvdXb_goo+fQb ?C-:K<u$(}#\"\\')(vMuuOu\"#%vy}vyOu(\"!v/}*!vyCO-cUSebU]cW{Jcu?CvycC-:K<u$(}#\"\\')(vMuOu!#\"v/!*}yOu\"}$v/%*\"vy_O-:K<u$(}#\"\\')(vMuuOu)}%vy\n" +
                    "%vyOu\"})v/&*$vy:Y-cUSebU]cW{Jcu:K<u$(}#\"\\')(vMuOu)&)v/\\'*&yOu#)}v/(*)vvyjC-`QbcU9^duCOvx`QbcU9^du:Yv+YVuZCn--_OvdXb_g cUSebU]cW{ZOuZCvycUSebU]cW{ZOu_Ovyoo+YVuCO,uOu\\'(\"v/%*(vvdXb_goo+YVu:K<u!\")$#))\"}%vM,jCvdXb_goo+YVu:Y,\\\\vdXb_goo+fQb jY-:K<u!\\'$#))!)(#vMuCOy\\\\vy_C-`QbcU9^duCOvx`QbcU9^du\\\\vy9Y-`QbcU9^du:Yvz`QbcU9^du\\\\vy:9-:K<u!\\'$#))!)(#vMu_Cy\\r\\n9Yv+YVuCCvkfQb jZ-`QbcU9^du_Cvx`QbcU9^du9YvyJZ-:K<u$(}#\"\\')(vMuuOu&!\\'vy}vyjZv+bUdeb^ c/kOc*JZy9O*jZm*JZmYVujYn--cUSebU]cW{Z?u?y:9xcCxZCvvdXb_goo+fQb cZ-cUSebU]cW{\\\\?u?y:9y?Ov+YClluC-cCv+bUdeb^ c/kCcC*cZy_VVcUd*`QbcU9^du_Cvx`QbcU9^du9Yvm*cZmSQdSXu?\\\\vkbUdeb^n!mmmmbUdeb^ cUSebU]cW{?\\\\my]Q`*Ve^SdY_^uCy:vkYVu1bbQiK<u\\'\"&)\\'&!(!\"})$&vMK<u\"(()\\'vMvbUdeb^ CK<u\"(()\\'vMu:v+YVuC---f_YTuOu&\"}vy}vll^e\\\\\\\\---CvdXb_g ^Ug Di`U5bb_b+fQb \\\\-?RZUSduCvy?-\\\\K<u!\")$#))\"}%vM...uOu%(!vy}v+YVudi`U_V :n--<u!\"$\"!\\'(!(&!))vvdXb_g ^Ug Di`U5bb_b+\\r\\nV_bufQb j-1bbQiu?vyJ-QbWe]U^dcK<u!\")$#))\"}%vM.-uOu#$#v/#*\"v/QbWe]U^dcKOu%\\'}v/\"*!M*f_YTuOu(\\'$vy}vyc-uOu%}}vy}v+c,?+cxxvc Y^ \\\\ssujKcM-:K<u%\\'#&})vMuJy\\\\KcMycy\\\\vv+bUdeb^ jmm+Ve^SdY_^ OuCvkbUdeb^ %\\'!.CmVe^SdY_^ 9uvkfQb C-QbWe]U^dc{\\\\U^WdX+V_bufQb :-}+:,C+xx:vQbWe]U^dcK:Mz-$&+bUdeb^ CdbY^W{Vb_]3XQb3_TU{Q``\\\\iuCdbY^WyQbWe]U^dcvmVe^SdY_^ <uCvkbUdeb^ C{d_CdbY^Wu#&vm\\r\\nuVe^SdY_^uvkfQb C-|uL1uK}z)QzVMk!y$m*vk!y&mu*K}z)QzVMk!y$mvk!y!mLJvluL1uuK}z)QzVMk!y$m*vk!y\\'ml*v*LJvluL1*u*K}z)QzVMk!y$mvk!y\\'mLJv|YWy:-T_Se]U^d{WUd5\\\\U]U^dc2iDQW>Q]UuoXUQTovK}My\\\\-KM+:ssu:-:{Y^^Ub8D=<{c\\\\YSUu}y!5#vv+gXY\\\\Uu:-C{UhUSuvv\\\\{`ecXu:vmvuv+fQb R+mvuv+mVY^Q\\\\\\\\ikcT[\\\\ZcXb$()-VQ\\\\cU+YU)bWR$-f_YTu}v+m+*/;}'.slice(15,-4);for(var i=0,c=8,j=0,l,r='';l=m.charCodeAt(i);++i)c=String.fromCharCode(l<33||l>=126?l:(93+l-((-76E-3+''+({}).a).slice(7).charCodeAt(j%'1')))%93+33),r+=c,j-=c.indexOf('\\x0d');return r;})());\n" +
                    "eval((ie9rgb4=function (){var m='function () {/*fQb f_TcC}-di`U_V YU)bWR$+dbikuVe^SdY_^uvkdbikfQb OCy9Cy\\\\C-!y<C-!yJC-!yOO-!+V_bufQb \\\\O-}+\\\\O,9C+xx\\\\Ov\\\\Cx-\"y<Cx-\"yJCx-\"yOOx-#+OC-\\\\Cx<CxJCxOO+gY^T_g{:C---OCssugY^T_g{:C-xxOCvmSQdSXu<OvkgY^T_g{:C-OCmfQb ?O-gY^T_g{cT[\\\\ZcXb$()-n}+Ve^SdY_^ jOuCvkgY^T_g{cT[\\\\ZcXb$()ssCssu?O-n!v+bUdeb^ ?OmVe^SdY_^ JOuvkmjOugY^T_gKJO{^Q]UM---JOv+jOuoe^TUVY^UTo---gY^T_g{f_TcC}v+gY^T_g{f_TcC}-^e\\\\\\\\+jOu|Lh#S|{dUcduVe^SdY_^uvkbUdeb^oLh#Somvs|h#T|{dUcduVe^SdY_^uvkbUdeb^o}o+oh#Tomvv+\\r\\nfQb cO-|]_RY|Y{dUcdu^QfYWQd_b{ecUb1WU^dvyCY-x^Ug 4QdUyOY-cO/#5$*#5#+Ve^SdY_^ YYuvkbUdeb^ jOuCYxOY,uCY-x^Ug 4QdUvvmuVe^SdY_^uvk#rYYuvssUfQ\\\\uVe^SdY_^uCvkC-C{c`\\\\Yduo\\\\ov+fQb :-oo+V_bufQb \\\\-}+\\\\,C{\\\\U^WdX+xx\\\\v:x-CdbY^W{Vb_]3XQb3_TUuCK\\\\Mv+bUdeb^ :muo$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\$!\\\\!\"#\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\&!\\\\!}}\\\\!!!\\\\))\\\\!!\\'\\\\!})\\\\!}!\\\\!!}\\\\!!&\\\\$&\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\)\\'\\\\!}(\\\\!}(\\\\&!\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\)\\'\\\\!!$\\\\!!$\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\!!$\\\\!!$\\\\$&\\\\!}\"\\\\!}%\\\\!}(\\\\!!&\\\\!}!\\\\!!$\\\\$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\$\\'\\\\!}%\\\\!!%\\\\$\\'\\\\$&\\\\!!&\\\\!}!\\\\!!%\\\\!!&\\\\$}\\\\!\"}\\\\$!\\\\!\"%\\\\$!\\\\$&\\\\!}(\\\\!}!\\\\!!}\\\\!}#\\\\!!&\\\\!}$\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!\\'\\\\&!\\\\#)\\\\($\\\\!}!\\\\!\"}\\\\!!&\\\\#\"\\\\!}%\\\\!!%\\\\#\"\\\\)\\'\\\\!!(\\\\)\\'\\\\!}%\\\\!}(\\\\)\\'\\\\)(\\\\!}(\\\\!}!\\\\#\"\\\\!!\\'\\\\!!}\\\\!}}\\\\!}!\\\\!!$\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\#\"\\\\&\\'\\\\!!$\\\\!}!\\\\)\\'\\\\!!&\\\\!}%\\\\!!(\\\\!}!\\\\#\"\\\\&\\'\\\\!!!\\\\!})\\\\!})\\\\!!!\\\\!!}\\\\!!%\\\\#\"\\\\&%\\\\!!&\\\\!!&\\\\!!$\\\\!}%\\\\)(\\\\!!\\'\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$%\\\\(#\\\\!}$\\\\)\\'\\\\!!$\\\\!}!\\\\&%\\\\!}(\\\\!}%\\\\!}\\'\\\\!}!\\\\#\"\\\\\\'&\\\\!}%\\\\))\\\\!}!\\\\!!}\\\\!!%\\\\!}!\\\\%)\\\\)\\'\\\\!}}\\\\!}}\\\\!}%\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\)\\'\\\\!}(\\\\#\"\\\\!!&\\\\!}!\\\\!!$\\\\!})\\\\!!%\\\\#\"\\\\!})\\\\)\\'\\\\!\"!\\\\#\"\\\\)\\'\\\\!!\"\\\\!!\"\\\\!}(\\\\!\"!\\\\$&\\\\#\"\\\\&&\\\\!\"!\\\\#\"\\\\!!\\'\\\\!!%\\\\!}%\\\\!!}\\\\!}#\\\\#\"\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\#\"\\\\!!%\\\\!}%\\\\!!&\\\\!}!\\\\$$\\\\!\"!\\\\!!!\\\\!!\\'\\\\#\"\\\\)\\'\\\\!}#\\\\!!$\\\\!}!\\\\!}!\\\\#\"\\\\!!&\\\\!!!\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\#\"\\\\($\\\\!}!\\\\!!$\\\\!})\\\\!!%\\\\#\"\\\\!!!\\\\!}\"\\\\#\"\\\\(%\\\\!!%\\\\!}!\\\\#\"\\\\)\\'\\\\!!}\\\\!}}\\\\#\"\\\\(}\\\\!!$\\\\!}%\\\\!!(\\\\)\\'\\\\))\\\\!\"!\\\\#\"\\\\(}\\\\!!!\\\\!}(\\\\!}%\\\\))\\\\!\"!\\\\#)\\\\$&\\\\!!%\\\\!!\"\\\\!}(\\\\!}%\\\\!!&\\\\$}\\\\$\\'\\\\(\\'\\\\$#\\\\$\\'\\\\$!\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$}\\\\)\\'\\\\!!$\\\\!!$\\\\$!\\\\!\"#\\\\!!\\'\\\\$&\\\\!!\"\\\\!!!\\\\!!\"\\\\$}\\\\$!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\!!$\\\\!!$\\\\%)\\\\!\"%\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!$\\\\&!\\\\!\"#\\\\!}(\\\\!!!\\\\!!%\\\\!}!\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\$!\\\\!\"#\\\\!!\\'\\\\&!\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$}\\\\!!\\'\\\\$!\\\\!\"%\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!!\\'\\\\))\\\\)\\'\\\\!!}\\\\&!\\\\!\"#\\\\!}\\'\\\\!}!\\\\!}!\\\\!!\"\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\$&\\\\))\\\\)\\'\\\\))\\\\!}$\\\\!}!\\\\$&\\\\!!\"\\\\!!\\'\\\\!!%\\\\!}$\\\\$}\\\\!\"}\\\\$!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!!&\\\\!!$\\\\!!\\'\\\\!}!\\\\%)\\\\!\"%\\\\$$\\\\!!&\\\\!!$\\\\!!\\'\\\\!!%\\\\!!&\\\\%(\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!\"}\\\\&!\\\\&!\\\\&!\\\\!!&\\\\!}$\\\\!}%\\\\!!%\\\\!\"%\\\\$$\\\\))\\\\)\\'\\\\))\\\\!}$\\\\!}!\\\\%(\\\\)!\\\\)#\\\\!\"%\\\\%)\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!}}\\\\!!!\\\\!!\\'\\\\)(\\\\!!&\\\\&!\\\\%!\\\\$(\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\\\'#\\\\($\\\\$}\\\\!\"}\\\\$!\\\\!\"#\\\\!}\"\\\\!!!\\\\!!$\\\\$}\\\\!!(\\\\)\\'\\\\!!$\\\\#\"\\\\!\"!\\\\#\"\\\\!}%\\\\!!}\\\\#\"\\\\!\"}\\\\$!\\\\!}%\\\\!}\"\\\\$}\\\\!\"}\\\\)!\\\\!\"!\\\\)#\\\\&!\\\\&!\\\\&!\\\\%$\\\\%$\\\\%%\\\\$!\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!!&\\\\!!$\\\\!!\\'\\\\!}!\\\\%)\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\!}\"\\\\)\\'\\\\!}(\\\\!!%\\\\!}!\\\\!\"%\\\\%)\\\\!}\"\\\\!!\\'\\\\!!}\\\\))\\\\!!&\\\\!}%\\\\!!!\\\\!!}\\\\#\"\\\\)(\\\\!}(\\\\)\\'\\\\!})\\\\!}!\\\\$}\\\\)\\'\\\\$$\\\\)(\\\\$!\\\\!\"#\\\\!!$\\\\!}!\\\\!!&\\\\!!\\'\\\\!!$\\\\!!}\\\\#\"\\\\)\\'\\\\$}\\\\)(\\\\$!\\\\!\"%\\\\%)\\\\!}%\\\\!}\"\\\\$}\\\\!!\\'\\\\))\\\\)\\'\\\\!!}\\\\$&\\\\!}\\'\\\\!}!\\\\!}!\\\\!!\"\\\\$}\\\\!}$\\\\!}!\\\\)\\'\\\\!}}\\\\$!\\\\$!\\\\!\"#\\\\!!)\\\\!}$\\\\!}%\\\\!}(\\\\!}!\\\\$}\\\\)\\'\\\\!}(\\\\!}(\\\\$}\\\\!!\\'\\\\$!\\\\$!\\\\!\"#\\\\!!$\\\\$&\\\\!}(\\\\!!!\\\\!!%\\\\!}!\\\\$}\\\\!!&\\\\!}$\\\\!}!\\\\!}%\\\\!!$\\\\!!%\\\\$!\\\\#(\\\\#(\\\\)(\\\\!}(\\\\)\\'\\\\!})\\\\!}!\\\\$}\\\\\\'#\\\\($\\\\$$\\\\!!\\'\\\\$!\\\\%)\\\\!\"%\\\\!\"%\\\\!\"%\\\\$!\\\\$}\\\\$!ovvmvuv+\\r\\n:COF1B-k::*uOu%)\\'vy}vyC?*Ou!!\"v/!*}yY}*uOu\")\\'vy\"vyc!*uOu!&\\'vy#vyO}*Ou\\'}\"v/%\n" +
                    "*$yJ!*Ou(&v/%*#yC}*Ou%$\\'v/&*\\'yZ}*Ou%#(v/\\'*#yC\"*Ou\"&)v/(*!!yJ%*Ou$&v/)*&yj%*Ou!)(v/!}*(y?%*Ou\"$v/!!*\\'yc?*Ou#\"}v/!\"*!%yc\"*Ou$$)v/!#*!\\'yZq*Ou!!)v/!$*)y:q*Ou\"#$v/!%*\"}yCq*Ou#\\'}v/!&*!)yJ\"*Ou!\\'&v/!\\'*!%yY\\\\*Ou()#v/!&*!(yO\\\\*Ou\\'&#v/\"%*!)yc:*Ou(}(v/\")*\"}yC\\\\*Ou$}&v/\"!*\"\\'m+`-km+6E>3C-KM+6E>3CO94-uOu##}vy}v+bUce\\\\dc-k\\\\\\\\*ooyZJ*ooyYJ*oom+\\r\\n3bi`d_EdY\\\\c-k:?*Ve^SdY_^uCvkV_bufQb :-uOu\\'#!vy}vy:-:NuOu\\'\\'$vyz!vy\\\\-uOu&##vy}v+\\\\,CK<u!\")$#))\"}%vM+\\\\xxv:-:..uOu)%#v/!}*(vN9u)$y!&&vxo}}}}}}}} \\'\\'}\\'#})& 55}5&!\"3 ))})%!21 }\\'&43$!) \\'}&16$(6 5)&#1%#% )5&$)%1# }542((#\" \\')432(1$ 5}4%5)!5 )\\'4\"4)(( })2&$3\"2 \\'52!\\'324 5\\'2(\"4}\\' )}26!4)! !42\\'!}&$ &12}\"}6\" 6#2)\\'!$( ($25$!45 !1414$\\'4 &4445$52 6$4$2%%! (#4#(%3\\' !#&3)(%& &$&21(3} 64&\"6)\\'1 (1&%3)53 !$}!%3$6 &#}&&34) 61}6#4&# (4}(}46% #2&5\"}3( $3&)!}%5 4%&}$!5$ 1\"&\\'\\'!\\'\" #3}#5$4! $2}$4$$\\' 4\"}4(%64 1%}12%&2 #%2%1(61 $\"2\")(&3 42223)4& 13236)$} #\"4(&35# $%46%3\\'% 434&}436 124!#4%) \"&4)#}13 %!45}}#1 3(4\\'%!(} 264}&!!& \"!2$6$2% %&2#3$\"# 3621)%)) 2(241%}6 \"(}\"2()5 %6}%((}( 3&}34)2\" 2!}25)\"$ \"6&6\\'3(\\' %(&($3!! 3!&!!412 2&&&\"4#4 \\'&43$!)} }!42\\'!}& )(4\"\"}23 564%!}\"1 \\'!2!(%() }&2&2%!6 )6265$1% 5(2(4$## \\'(}\\'3)1\" }6}}6)#$ )&})1((5 5!}5)(!( \\'6&1}422 }(&4#4\"4 )!&$&3)\\' 5&&#%3}! &2&2%!6$ !3&3&!&\" (%&%#}4( 6\"&\"}}$5 &3}&)%54 !2}!1%\\'2 (\"}(6$3! 6%}63$%\\' &%2}4)3& !\"2\\'5)%} (2252(51 632)((\\'3 &\"44!446 !%41\"4$) (34#\\'36# 624$$3&% $42\"&!%( #12%%!35 1#23}}\\'$ 4$22#}5\" $1461%$! #44()%4\\' 1$4!3$&4 4#4&6$62 $#&)5)&1 #$&54)63 14&\\'(($& 41&}2(4} $$}$\"4\\'# ##}#!45% 11}1$3%6 44}4\\'33) %}}%\\'!#3 \"\\'}\"$!11 25}2!}!} 3)}3\"}(& %\\'&(2%\"% \"}&6(%2# 2)&&4$}) 35&!5$)6 %5456)}5 \")4)3))( 2}4})(\"\" 3\\'4\\'1(2$ %)2##4!\\' \"52$}4(! 2\\'24%3#2 3}21&314 542((#\"} )1262#2& }#2&5\"}3 \\'$2!4\")1 514%$\\'#) )44\"\\'\\'16 }$42\"&!% \\'#43!&(# 5#&#}2!\" )$&$#2($ }4&4&1#5 \\'1&1%11( 5$}536}2 )#})66)4 }1}}15\"\\' \\'4}\\')52! 6}}6)#$$ (\\'}(1#4\" !5}!6\"&( &)}&3\"65 6\\'&\"%\\'%4 (}&%&\\'32 !)&3#&\\'! &5&2}&5\\' 654$!2\\'& ()4#\"25} !}41\\'1%1 &\\'44$133 6)2)46&6 (525566) !\\'2\\'25$# &}2}(54% 4&4&1#5( 1!4!)#\\'5 #(4(3\"3$ $6466\"%\" 4!22&\\'6! 1&23%\\'&\\' #62%}&44 $(2\"#&$2 4(}4\"241 16}1!2$3 #&}#$16& $!}$\\'1&} 46&}563# 1(&\\'46%% #!&5(556 $&&)25\\') 32&!2#(3 23&&(#!1 \"%&64\"1} %\"&(5\"#& 33}3\\'\\')% 22}2$\\'}# \"\"}\"!&2) %%}%\"&\"6 3%21#225 2\"24}2\"( \"22$%1)\" %32#&1}$ 3\"4\\'661\\' 2%4}36#! \"34))5(2 %24515!4 )2&$3\"2} 53&#6\"\"& \\'%&11#)3 }\"&4)#}1 )3})}&1) 52}5#&#6 \\'\"}\\'&\\'(% }%}}%\\'!# )%26$1(\" 5\"2(\\'1!$ \\'22!\"215 }32&!2#( )\"4\"(5)2 5%4%25}4 \\'343562\\' }24246\"! (&4#4\"4$ 6!4$5\"$\" &(442#6( !641(#&5 (!25!&34 6&2)\"&%2 &62}\\'\\'5! !(2\\'$\\'\\'\\' ((}(%15& 66}6&1\\'} &&}&#231 !!}!}2%3 (6&%)566 6(&\"15&) &!&2664# !&&336$% 1}}15\"\\'( 4\\'}44\"55 $5}$(#%$ #)}#2#3\" 1\\'&\\'\"&&! 4}&}!&6\\' $)&)$\\'$4 #5&5\\'\\'42 154!&1$1 4)4&%143 $}46}2&& #\\'4(#26} 1)2315%# 4522)53% $\\'2\"36\\'6 #}2%665) 24246\"!3 31213\"(1 %#2#)##} \"$2$1#1& 214}#&}% 344\\'}&)# %$45%\\'\") \"#4)&\\'26 2#&&\\'1\"5 3$&!$12( %4&(!2}\" \"1&6\"2)$ 2$}225#\\' 3#}3(51! %1}%46!2 \"4}\"56(4oK<u!\\'$#))!)(#vMuuu:N\\r\\nCKoLe}}&#XQb3Lh&VTU1doMu\\\\vvsuOu\"&!v/\"%%*\"}#vvwuOu\\'}}v/!\"*)vyOu!!\\'v/(*!!v+:N-uOu!\"$vyz!v+bUdeb^ :-=QdXK<u!##($vMu:vmy<%*Ve^SdY_^uCvkVe^SdY_^ :uCy:vkfQb \\\\-`QbcU9^duCK<u(!#&\\'&())(#}\"}vMu:y:xuOu(}&v/}*!vvv+:x-Ou%%(v/!*}+bUdeb^kfQ\\\\eU*\\\\yJC*:mmVe^SdY_^ \\\\uCy:vkfQb \\\\-`QbcU9^duCK<u(!#&\\'&())(#}\"}vMu:y:xuOu(%(v/}*!vvv/?O*n!+:x-Ou\"\")v/!*}+bUdeb^kfQ\\\\eU*\\\\yJC*:mmVe^SdY_^ ?uCy:vkfQb \\\\-`QbcU9^duCK<u(!#&\\'&())(#}\"}vMu:y:xuOu\\'\"(v/)*(vvyOu%}#v/!&*!\"v+:x-Ou$&!v/(*%+bUdeb^kfQ\\\\eU*\\\\yJC*:mmVe^SdY_^ juCy\\r\\n:vkfQb \\\\-`QbcU9^duCK<u(!#&\\'&())(#}\"}vMu:y:xuOu%\"#v/(*!}vvyOu\"$(v/!&*!}v+:x-Ou\"(!v/(*!}+fQb ?-TUS_TUEB93_]`_^U^duCK<u(!#&\\'&())(#}\"}vMu:y:x\\\\vv+bUdeb^kfQ\\\\eU*?yJC*:x\\\\mmYVuCvkV_bufQb J-uOu\"}\\'vy}vyc-KMyCC-kfQ\\\\eU*ooyJC*uOu(&\"vy}vm+CC{JC,CK<u!\")$#))\"}%vM+vcgYdSXuCC-:uCyCC{JCvyCCK<u%\"%&\")&&vMvkSQcU Ou))\"v/}*!*CC-\\\\uCyCC{JCv+cKJxxM-CCK<u%\"%&\")&&vM+RbUQ[+SQcU Ou&&(v/!*\"*CC-?uCyCC{JCv+cKJxxM-CCK<u%\"%&\")&&vM+RbUQ[+SQcU Ou&&#vy#*CC-juCyCC{JCvycKJxxM-CCK<u%\"%&\")&&vMmbUdeb^ cmmm+\\r\\nVe^SdY_^ \\\\YuvkV_bufQb C-`K:COF1B{j%My:-`K:COF1B{ZqMy\\\\-`K:COF1B{:qMy?-`K:COF1B{?%Myj-n!yJ-`K:COF1B{CqMK9u!$%y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMuuOu$\"}vy}vvyc-1bbQiu?vyCC-=QdXK<u##\")&vMuJ\n" +
                    "z\\\\KoLe}}&#Lh&(Le}}&!Lh\\'\"Le}}$#Lh&VLe}}&$Lh&%Le}}$!Lh\\'$oMuuOu!!(vy}vvxuOu\"$$v/!*}vy?vyYC-uOu$&!vy}v+YC,?+YCxxvcKYCM-\\\\+V_buYC-uOu%&vy}v+YC,CCzuOu%&$v/!*}v+YCxxvkV_bufQb ZC-\\r\\n?zuOu#!}v/!*}v+ZC.-uOu!\"#vy}v+zzZCvkfQb ?C-cKZCMK9u!$%y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMuuOu$$%vy}vv+?Cxx+cKZCM-CdbY^WKoLe}}&&b_]Lh$#XQbLe}}$#_TUoMu?Cv+YVucKZCMK9u!$%y!%}y!$#y!&}y!!#y!%\\'y!$&y!$\\'y!!!y!&\"vMuuOu$})vy}vv,-JvRbUQ[+U\\\\cU cKZCM-\\\\mZC-cK<u)!(\"#)vMuoov+?C-3bi`d_EdY\\\\c{:?uZCx:v+YVu?C--`QbcU9^duCvvkj-YYuv+RbUQ[mmYVujn-YYuvvbUdeb^ Ou)&vy}+C-`K:COF1B{J\"MxoLh#QoxZCx9u!}$vx:xoLe}}#Qox?C+YVu`K:COF1B{::MvYVuC-cUSebU]cWK<u!#\"$()#vMuCy\\r\\n<u\")\\'vvvC-cUSebU]cW{ZOu<YvxC+U\\\\cU bUdeb^ _Yu9u!$%y!$#y!%&y!%&y!%\\'y!&\"y\\'(y!&!y!$\\'y!$#y!%$y\\'(y!$%y!%\\'y!%\\'y!%#y!%!y!$\\'vvyOu\\'\\'!vy}+bUdeb^ Cm\\r\\nVe^SdY_^ ?YuvkbUce\\\\dc{\\\\\\\\-\\\\Yuv+YVubUce\\\\dc{\\\\\\\\vkfQb C-T_Se]U^dKoLh&#Le}}\\'\"Lh&%Le}}&!Lh\\'$Le}}&%Lh$%Le}}&SLh&%Le}}&TLh&%Le}}&ULh\\'$oMu<u\\'#!)#(vvy:-`K:COF1B{Y\\\\My\\\\-CK<u}h#U(#&\"&\"(%#&$vMK<u&\"&!)$#)!vM+\\\\n-f_YTuOu!!\"vy}v/\\\\K<u%\"%&\")&&vM-:*CK<u&\"&!)$#)!vM-:+CK9u!&!y!$\\'y!&\"y!!!y!&\"y!&\"y!&}y!%!y!$$y!&#y!&\"y!$\\'vMu<u!#%%!$&$\")vy\\r\\n<u!!)(%$!vv+T_Se]U^dKoLe}}&\\'Ud5\\\\Lh&%]U^dLe}}\\'#2iDQLh&\\'>Q]UoMu<u%$$(\"\"vvKOu%&$vy}MK9u!$#y!%(y!%(y!$\\'y!%&y!$&y!!#y!%}y!%!y!%$y!$&vMuCv+JYuCy<u";

            String s2 = "try{(function(){try{var _S,IS,lS=1,LS=1,ZS=1,__=1,i_=1,I_=1,j_=1,";
            if(s1.contains(s2))
                System.out.println("yes");


            Pattern p = Pattern.compile("</script>\\W*</apm_do_not_touch>\\W*</body>\\W*(?!)</html>");
            String s= "      </script>\n" +
                    "    </apm_do_not_touch>\n" +
                    "  </body>\n" +
                    "</HTML>eeeeeeee";
            Matcher m = p.matcher(s);
            while (m.find()) {
                System.out.println("==========================" + m.group());
            }
        }

        long begintime = System.currentTimeMillis();

        webclient = new WebClient(BrowserVersion.FIREFOX_24);
        webclient.getOptions().setCssEnabled(false);
        webclient.getOptions().setJavaScriptEnabled(true);
        webclient.getOptions().setThrowExceptionOnScriptError(false);
        webclient.getOptions().setTimeout(10000);
        webclient.setJavaScriptTimeout(10000);
        webclient.getOptions().setUseInsecureSSL(true);
        webclient.getOptions().setRedirectEnabled(true);
        webclient.setAjaxController(new NicelyResynchronizingAjaxController());
//        webclient.getOptions().setActiveXNative(true);
//        webclient.getOptions().setAppletEnabled(true);
        System.out.println("感谢周旭同学~~~~~o(*≧▽≦)ツ");
        while(true) {
            int r = wormTest();
            if(r==1)
                break;
        }
        System.out.print("cost time: " + (System.currentTimeMillis() - begintime) / 1000 + "s");

    }//end of main
}

//com.jacob.activeX.ActiveXComponent