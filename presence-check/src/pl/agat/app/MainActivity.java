package pl.agat.app;

import java.util.ArrayList;

import com.app.psalmodia.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	Database db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		db = new Database(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void window_presenceCheck(View v) {
		
		setContentView(R.layout.presence_check);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.voices, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
	public void viewMembers(View v) {
		ArrayList<String> members = new ArrayList<String>();
		members.addAll(db.getAllMemberNames());
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
	            android.R.layout.simple_list_item_1,
	            members);
		//setListAdapter(adapter);
		setContentView(R.layout.member_list);
		ListView list=(ListView) findViewById(R.id.listView1);
		adapter.setDropDownViewResource(android.R.layout.list_content);
		list.setAdapter(adapter);
	}
	public void displayDb(View v) {
		Toast.makeText(this,"Displaying",Toast.LENGTH_SHORT).show();
	}
	public void deleteMember(View v) {
		db.deleteMember();
		Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
		viewMembers(v);
	}
	public void addMember(View v) {

		EditText name=(EditText) findViewById(R.id.editText1);
		EditText surname=(EditText) findViewById(R.id.editText2);
		EditText voice=(EditText) findViewById(R.id.editText3);
		EditText ph_no=(EditText) findViewById(R.id.editText4);
		Member nowy=new Member(name.getText().toString(),surname.getText().toString(),Integer.parseInt(voice.getText().toString()),ph_no.getText().toString());
		db.addMember(nowy);
		viewMembers(v);
		
	}
	public void setAddMember(View v) {
		setContentView(R.layout.person_add);
	}
}
