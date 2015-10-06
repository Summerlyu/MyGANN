package uk.ac.gann.config;
/**
 * @author Summer Lyu
 * 
 */
public final class VersionInfo {
    //application info
	public final static String APP_NAME="Data mining for shopping pattern analysis";
    public final static String APP_VERSION="1.0";
    public final static String APP_STATUS="Beta";
    public final static String APP_BUILDATE="20/02/2014";
    	
    //application author
    public final static String APP_AUTHOR="Summer Lyu";
    public final static String APP_WORKSTUDIO="University of Ulster";
    
    public final static String buildFooterStr()
    {
    	StringBuffer sb=new StringBuffer();

    	sb.append(APP_NAME);
    	sb.append(" "+"(Version:"+APP_STATUS+APP_VERSION);
    	sb.append(" "+APP_BUILDATE+")");
    	sb.append("&nbsp;&nbsp;&nbsp;&nbsp;email:&nbsp;<a href=\"mailto:lv-d@email.ulster.ac.uk\">"+APP_AUTHOR+"</a><br/>");
    	sb.append("(C)&nbsp;"+APP_WORKSTUDIO+"&nbsp;&nbsp;");

    	return sb.toString();	
    }
}
