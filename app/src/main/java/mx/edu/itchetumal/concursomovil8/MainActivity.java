package mx.edu.itchetumal.concursomovil8;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private EditText txtCodigo;
    private EditText txtNombre;
    private TextView txtResultado;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnConsultar;
    private FrameLayout actividad_uno;
    private SQLiteDatabase db;
    private Fragment fragment = null;
    boolean fragmentTransaction = false;
    TextView t;
    TextView usFrag1;
    public static String user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getExtras().getString("USUARIO");

        Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);



        navView = (NavigationView)findViewById(R.id.navview);

        fragmentTransaction = true;
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {




                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new Fragment1();
                                fragmentTransaction = true;

                                break;
                            case R.id.menu_seccion_2:
                                fragment = new Fragment2();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new Fragment3();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_4:
                                fragment = new Fragment4();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_5:
                                fragment = new Fragment5();
                                fragmentTransaction = true;
                                break;
                        }

                        if (fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.conten, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
        init_home();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                t = (TextView)findViewById(R.id.Ejemplo);
                t.setOnClickListener(this);
                t.setText(user.toString());
                extra(user.toString());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String extra(String us){
        return us;
    }
    public void init_home(){
        fragment = new HomePage();
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.conten, fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Ejemplo:
                init_home();
                drawerLayout.closeDrawers();
                break;
        }
    }
}