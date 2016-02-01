package com.example.portscanner;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
 public final static String EXTRA_MESSAGE = "com.example.portscanner.MESSAGE";
 private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
 TextView textResponse;
 TextView load;
 EditText ip; 
 Button button,stop;
 Button btnClosePopup;
 CheckBox echo,ftp_data,ftp_control,ssh,telnet,smtp,dns,finger,http,rip,mysql,ldap,https,dhcp;
 ArrayList<Integer> arr = new ArrayList<>();
 MyClientTask myClientTask;
 HashMap<Integer, String> hm;
 public static boolean validate(final String ip) {
	    return PATTERN.matcher(ip).matches();
	}
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  hm = new HashMap<Integer,String>();
  hm.put(7, "Echo");
  hm.put(20,"FTP Data");//FTP data
  hm.put(21,"FTP Control");//FTP control
  hm.put(22,"SSH");//SSH 
  hm.put(23,"Telnet");//Telnet
  hm.put(25,"Smtp");//SMTP
  hm.put(53,"DNS Server");//DNS Server
  hm.put(79,"FINGER");//Finger
  hm.put(80,"HTTP");//HTTP
  hm.put(520,"RIP");//SFTP
  hm.put(3306,"MySql");//Mysql
  hm.put(389,"LDAP");//LDAP
  hm.put(443,"HTTPS");//HTTPS
  hm.put(67,"DHCP");//DHCP Server 
  
  ip = (EditText)findViewById(R.id.ip);
  button = (Button)findViewById(R.id.button);
  echo = (CheckBox)findViewById(R.id.echo);
  ftp_data = (CheckBox)findViewById(R.id.ftp_data);
  ftp_control = (CheckBox)findViewById(R.id.ftp_control);
  ssh = (CheckBox)findViewById(R.id.ssh);
  telnet = (CheckBox)findViewById(R.id.telnet);
  smtp = (CheckBox)findViewById(R.id.smtp);
  dns = (CheckBox)findViewById(R.id.dns);
  finger = (CheckBox)findViewById(R.id.finger);
  http = (CheckBox)findViewById(R.id.http);
  rip = (CheckBox)findViewById(R.id.rip);
  mysql = (CheckBox)findViewById(R.id.sql_server);
  ldap = (CheckBox)findViewById(R.id.ldap);
  https = (CheckBox)findViewById(R.id.https);
  dhcp = (CheckBox)findViewById(R.id.dhcp);
  load = (TextView)findViewById(R.id.loading);
  
  OnClickListener buttonConnectOnClickListener = 
   new OnClickListener(){
	  
    @Override
    public void onClick(View arg0) {
       if(validate(ip.getText().toString())){
          myClientTask = new MyClientTask(
          ip.getText().toString());
          myClientTask.execute();
          button.setVisibility(View.INVISIBLE);
          load.setText("Please wait..");
       }
       else{
    	   Toast.makeText(getApplicationContext(), "Incorrect Ip", Toast.LENGTH_LONG).show();
       }
    }};
   button.setOnClickListener(buttonConnectOnClickListener);
  
   echo.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v) {
      if(echo.isChecked()){
    	  arr.add(7);
      }
      else{
    	  if(arr.contains(7)){
    			 arr.remove( arr.indexOf(7));
    	  }
      }
	}
   });
   
   ftp_data.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		if(ftp_data.isChecked()){
			arr.add(20);
		}
		else{
			if(arr.contains(20)){
				arr.remove(arr.indexOf(20));
			}
		}
	}
   });
   
   ftp_control.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
      if(ftp_control.isChecked()){
    	  arr.add(21);
      }		
      else{
    	  if(arr.contains(21)){
    		  arr.remove(arr.indexOf(21));
    	  }
      }
	}
   });
   
   ssh.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
     if(ssh.isChecked()){
    	 arr.add(22);
     }	
     else{
    	 if(arr.contains(22)){
    		 arr.remove(arr.indexOf(22));
    	 }
     }
	}
   });
   
   telnet.setOnClickListener(new View.OnClickListener() {
		
	@Override
	public void onClick(View v) {
     if(telnet.isChecked()){
    	 arr.add(23);
     }	
     else{
    	 if(arr.contains(23)){
    		 arr.remove(arr.indexOf(23));
    	 }
     }
	}
   });
   
   smtp.setOnClickListener(new View.OnClickListener() {
		
	@Override
	public void onClick(View v) {
     if(smtp.isChecked()){
    	 arr.add(25);
     }	
     else{
    	 if(arr.contains(25)){
    		 arr.remove(arr.indexOf(25));
    	 }
     }
	}
   });
   
   dns.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	     if(dns.isChecked()){
	    	 arr.add(53);
	     }	
	     else{
	    	 if(arr.contains(53)){
	    		 arr.remove(arr.indexOf(53));
	    	 }
	     }
		}
	   });
   
   finger.setOnClickListener(new View.OnClickListener() {
		
 		@Override
 		public void onClick(View v) {
 	     if(finger.isChecked()){
 	    	 arr.add(79);
 	     }	
 	     else{
 	    	 if(arr.contains(79)){
 	    		 arr.remove(arr.indexOf(79));
 	    	 }
 	     }
 		}
 	   });
   http.setOnClickListener(new View.OnClickListener() {
		
 		@Override
 		public void onClick(View v) {
 	     if(http.isChecked()){
 	    	 arr.add(80);
 	     }	
 	     else{
 	    	 if(arr.contains(80)){
 	    		 arr.remove(arr.indexOf(80));
 	    	 }
 	     }
 		}
 	   });
   
   rip.setOnClickListener(new View.OnClickListener() {
		
 		@Override
 		public void onClick(View v) {
 	     if(rip.isChecked()){
 	    	 arr.add(520);
 	     }	
 	     else{
 	    	 if(arr.contains(520)){
 	    		 arr.remove(arr.indexOf(520));
 	    	 }
 	     }
 		}
 	   });
   
   mysql.setOnClickListener(new View.OnClickListener() {
		
 		@Override
 		public void onClick(View v) {
 	     if(mysql.isChecked()){
 	    	 arr.add(3306);
 	     }	
 	     else{
 	    	 if(arr.contains(3306)){
 	    		 arr.remove(arr.indexOf(3306));
 	    	 }
 	     }
 		}
 	   });
    
   ldap.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	     if(ldap.isChecked()){
	    	 arr.add(389);
	     }	
	     else{
	    	 if(arr.contains(389)){
	    		 arr.remove(arr.indexOf(389));
	    	 }
	     }
		}
	   });
   
   https.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	     if(https.isChecked()){
	    	 arr.add(443);
	     }	
	     else{
	    	 if(arr.contains(443)){
	    		 arr.remove(arr.indexOf(443));
	    	 }
	     }
		}
	   });
   dhcp.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	     if(dhcp.isChecked()){
	    	 arr.add(67);
	     }	
	     else{
	    	 if(arr.contains(67)){
	    		 arr.remove(arr.indexOf(67));
	    	 }
	     }
		}
	   });
    
  }

  private PopupWindow pwindo;
  TextView disp;
  private void initatePopupwindow(String response){
	         //load.setText("");
	         try{
	           LayoutInflater inflater = (LayoutInflater) MainActivity.this
			  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			   View layout = inflater.inflate(R.layout.screen_popup,
			  (ViewGroup) findViewById(R.id.popup_element));
			    pwindo = new PopupWindow(layout, 500, 900, true);
			    pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
			    btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
			    btnClosePopup.setOnClickListener(cancel_button_click_listener);
			    disp = (TextView)layout.findViewById(R.id.outputdisplay);
			  } catch (Exception e) {
			  e.printStackTrace();
			  }
			  }

			  private OnClickListener cancel_button_click_listener = new OnClickListener() {
			  public void onClick(View v) {
			    pwindo.dismiss();
                finish();
                System.exit(0);
			  }
	 };
 
 public class MyClientTask extends AsyncTask<Void, Void, Void> {
  
  String dstAddress;
  String response = "";
  Context mdialog;
  MyClientTask(String addr){
   dstAddress = addr;
  }
  
  @Override
  protected Void doInBackground(Void... arg0) { 
   Socket socket = null;
   DatagramSocket ds = null;
	for(int i=0;i<arr.size();i++){   
		if(arr.get(i) == 67 || arr.get(i) == 520){
			try{
			    ds = new DatagramSocket();
			    ds.connect(new InetSocketAddress(dstAddress, arr.get(i)));
			    ds.setSoTimeout(1000);
			    if(ds.isConnected())
		          response += hm.get(arr.get(i))+"     [UP]\n";
			}
			catch(Exception e){
				 response += hm.get(arr.get(i))+"     [DOWN]\n";
			}
			finally {
				if(ds != null){
					try{
				      ds.close();
					}catch(Exception e){}
				}
			}
		}else{
		  try{
             socket = new Socket();
             socket.connect(new InetSocketAddress(dstAddress, arr.get(i)),1000);
             response += hm.get(arr.get(i))+"     [UP]\n";
		   }
          catch (UnknownHostException e) {
		    // TODO Auto-generated catch block
		    response += hm.get(arr.get(i))+"    [DOWN]\n";
		   } 
		  catch (Exception e) {
		    // TODO Auto-generated catch block
		    response += hm.get(arr.get(i))+"    [DOWN]\n";
		   }
		  finally{
		    if(socket != null){
		     try {
		      socket.close();
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		     }
		    }
		   }
		}//else closed
	}
   return null;
  }

  @Override
  protected void onPostExecute(Void result) {
	 //load.setText("");
     initatePopupwindow(response);
     disp.setText(response);
     super.onPostExecute(result);
  }
  
 }
}
