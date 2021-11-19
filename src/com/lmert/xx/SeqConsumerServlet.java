package com.lmert.xx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeqConsumerServlet
 */
@WebServlet(
		description = "Consume and locally store fist value of the sequence", 
		urlPatterns = { "/SeqConsumerServlet" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "http://scatha.lmert.com:30880/next_seq.php", description = "REST URL for the sequence")
		})
public class SeqConsumerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private URL url;
	
	
	private int nextValue() throws IOException {
	      URLConnection con = url.openConnection();
	      con.setUseCaches(false);
	      con.connect();
	      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      String line = in.readLine();
	      return Integer.parseInt(line);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeqConsumerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
		   synchronized (this) {
		      url = new URL(config.getInitParameter("url"));
		      int initialValue = nextValue();
		      config.getServletContext().setAttribute("com.lmert.seqConsumer.initialValue",initialValue);
		   }
		} catch (IOException ex) {
		   throw new RuntimeException(ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "text/plain");
		response.getWriter().println(nextValue());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setHeader("Content-Type", "text/plain");
       response.getWriter().println(nextValue());
	}

	
}
