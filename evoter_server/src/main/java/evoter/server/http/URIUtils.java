package evoter.server.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import evoter.share.utils.URIRequest;

/**
 * 
 * @author btdiem
 *
 */
@Service
public class URIUtils {
	
	public final static String CONTENT_TYPE = "Content-Type";
	public final static String CONTENT_ENCODING = "Content-Encoding";
	public final static String APPLICATION_JSON = "application/json; charset=ISO-8859-1";
	public final static String APPLICATION_TEXT = "application/text";
	
	@Autowired
	private static HttpConnection httpConnection;
	
	public static boolean isLoginRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.LOGIN); 
	}

	public static boolean isLogoutRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.LOGOUT); 
	}
	
	public static boolean isGetAllSubjectRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.GET_ALL_SUBJECT); 
	}
	
	public static boolean isViewSubjectRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.VIEW_SUBJECT); 
	}
	
	public static boolean isDeleteSubjectRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.DELETE_SUBJECT); 
	}
	
	public static boolean isSearchSubjectRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.SEARCH_SUBJECT); 
	}
	
	public static boolean isGetAllSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.GET_ALL_SESSION); 
	}
	
	public static boolean isViewSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.VIEW_SESSION); 
	}
	
	public static boolean isCreateSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.CREATE_SESSION); 
	}
	
	public static boolean isActiveSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.ACTIVE_SESSION); 
	}

	public static boolean isInActiveSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.INACTIVE_SESSION); 
	}

	public static boolean isDeleteSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.DELETE_SESSION); 
	}
	
	public static boolean isUpdateSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.UPDATE_SESSION); 
	}
	
	public static boolean isAcceptSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.ACCEPT_SESSION); 
	}

	public static boolean isSearchSessionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.SEARCH_SESSION); 
	}

	public static boolean isGetAllQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.GET_ALL_QUESTION); 
	}
	
	public static boolean isViewQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.VIEW_QUESTION); 
	}
	
	public static boolean isCreateQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.CREATE_QUESTION); 
	}
	
	public static boolean isDeleteQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.DELETE_QUESTION); 
	}

	public static boolean isUpdateQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.UPDATE_QUESTION); 
	}
	
	public static boolean isSendQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.SEND_QUESTION); 
	}
	
	public static boolean isGetQuestionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.GET_QUESTION); 
	}

	public static boolean isStopSendQuestionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.STOP_SEND_QUESTION); 
	}
	
	public static boolean isSearchQuestionRequest(String URIString){
		
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.SEARCH_QUESTION); 
	}
	
	public static boolean isGetLatestQuestionRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.GET_LATEST_QUESTION); 
	}

	public static boolean isGetStatisticsRequest(String URIString){
		
		return (URIString != null) 
			&& URIString.contains(httpConnection.getContext()) 
			&& URIString.endsWith(URIRequest.GET_STATISTICS); 
	}
	
	public static Map<String, Object> getParameters(HttpExchange exchange) {
		
		Map<String,Object> parameters = new HashMap<String, Object>();
		try {
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(exchange.getRequestBody(), writer);
			String parameterMessage = writer.toString();
			StringTokenizer parameterValueToken = new StringTokenizer(parameterMessage,"&");
			while(parameterValueToken.hasMoreTokens()) {
				 String parameterValue = parameterValueToken.nextToken();
				 StringTokenizer value = new StringTokenizer(parameterValue,"=");
				 parameters.put(value.nextToken(), value.nextToken().replace("+", " ").replace("%40", "@"));
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return parameters;
		
	}
	
	public static void writeResponse(Object response, HttpExchange t) {
		
		byte[] responseBytes = response.toString().getBytes();
		
		
		try {

   		    OutputStream os = t.getResponseBody();   		    
   		    
   		    Headers responseHeaders = t.getResponseHeaders();
		    responseHeaders.set(CONTENT_TYPE, APPLICATION_JSON);
		    responseHeaders.set("Content-Encoding", "gzip");
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    GZIPOutputStream gos = new GZIPOutputStream(bos);
		    gos.write(responseBytes);
		    gos.close();
		    
		    
		    int len = bos.size();
		    t.sendResponseHeaders(200, len);
		    
	        os.write(bos.toByteArray());
			   		    
			try {				
			    os.flush();
                os.close();
			} catch (Exception e) {

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {


		}
	}
	
	public static void writeSuccessResponse(HttpExchange httpExchange){
		
		URIUtils.writeResponse(URIRequest.SUCCESS_MESSAGE, httpExchange);
	}
	
	public static void writeFailureResponse(HttpExchange httpExchange){
		
		URIUtils.writeResponse(URIRequest.FAILURE_MESSAGE, httpExchange);
	}
	
	public static boolean isResetPassword(String URIString) {
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.RESET_PASSWORD); 
	}
	
	public static boolean isRegister(String URIString) {
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.REGISTER); 
	}
	
	public static boolean isGetAllUserOfSubject(String URIString) {
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.GET_ALL_USERS_OF_SUBJECT); 
	}
	
	public static boolean isUpdateSubject(String URIString){
		return (URIString != null) 
				&& URIString.contains(httpConnection.getContext()) 
				&& URIString.endsWith(URIRequest.UPDATE_SUBJECT); 
		
	}
	
	
}
