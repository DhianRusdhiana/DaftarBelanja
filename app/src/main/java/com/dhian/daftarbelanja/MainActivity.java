package com.dhian.daftarbelanja;

import android.app.*;
import android.os.*;
import android.app.AlertDialog;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.*;
import java.util.*;
import android.support.design.widget.*;
import android.view.*;
import android.widget.*;
import android.content.DialogInterface;
import android.text.*;
import android.content.*;
import android.preference.*;
import android.content.SharedPreferences.Editor;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import android.support.v7.widget.helper.*;
import android.graphics.*;

public class MainActivity extends AppCompatActivity 
{
    RecyclerView recyclerView;
    private RecycleViewAdapter rvadapter;
    private int currentPosition = 0;
    private boolean enabled = false;
   private List<Data> data;
    private int indextA;
    private Paint p = new Paint();
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        data = PrefUtil.getAllValues(this);
        //SharedPreferences sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);

        //Boolean[] checkedStatus = new Boolean[data.size()];
        
        //for ( indextA = 0; indextA < checkedStatus.length; indextA++)
         // checkedStatus[indextA] = sharedPreferences.getBoolean(String.valueOf(indextA), false);
          
    
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        rvadapter = new RecycleViewAdapter(data, getApplication());
        recyclerView.setAdapter(rvadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        View v;
        ArrayList<Double> mannschaftsnamen = new ArrayList<Double>();
        EditText et;
        for (int i = 0; i < rvadapter.getItemCount(); i++) {
            //v = rvadapter.get(i, null, null);
            //et = (EditText) v.findViewById(i);
            mannschaftsnamen.add(Double.parseDouble(data.get(i).sJumlah));
    }
        double sum = 0;
        for(int i2 = 0; i2 < mannschaftsnamen.size(); i2++)
        {
            sum = sum + mannschaftsnamen.get(i2);
        }
            
        
            Toast.makeText(MainActivity.this,String.valueOf(sum),Toast.LENGTH_SHORT).show();
            
                
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.dialog_tambah, null);
                    final EditText tambahNamaBarang = (EditText) alertLayout.findViewById(R.id.tambah_namaBarang);
                    final EditText tambahJumlah = (EditText) alertLayout.findViewById(R.id.tambah_jumlah);
                    //final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater2 = getLayoutInflater();
                    View view2=inflater2.inflate(R.layout.header_dialog, null);
                    TextView title = (TextView)view2.findViewById(R.id.title);
                    title.setText("Tambah Barang");
                    alert.setCustomTitle(view2);
                    alert.setTitle("Tambah Barang");
                    // this is set the view from XML inside AlertDialog
                    alert.setView(alertLayout);
                    // disallow cancel of AlertDialog on click of back button and outside touch
                    alert.setCancelable(false);
                    
                    alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                
                            }
                        });

                    alert.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nama = tambahNamaBarang.getText().toString();
                                String jmlh = tambahJumlah.getText().toString();
                                
                                int item = rvadapter.getItemCount();
                                rvadapter.insert(item,new Data(nama,jmlh,"false"));
                                 rvadapter.notifyDataSetChanged();
                                PrefUtil.saveString(MainActivity.this, nama, jmlh);
                                      
                                
                            }
                        });
                    final AlertDialog dialog = alert.create();
                                        dialog.show();
                    
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE)
                        .setEnabled(false);
                    
                        
                    tambahNamaBarang.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before,
                                                      int count) {
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count,
                                                          int after) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                // Check if edittext is empty
                                if(tambahNamaBarang.getText().toString().length() >= 1 && (tambahJumlah .getText().toString().length() >= 1 )){
                                    ((AlertDialog) dialog).getButton(
                                        AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                    }else{
                                        ((AlertDialog) dialog).getButton(
                                            AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                    }

                            }
                        });
                    tambahJumlah.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before,
                                                      int count) {
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count,
                                                          int after) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                // Check if edittext is empty
                                if(tambahNamaBarang.getText().toString().length() >= 1 && (tambahJumlah .getText().toString().length() >= 1 )){
                                    ((AlertDialog) dialog).getButton(
                                        AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                                }else{
                                    ((AlertDialog) dialog).getButton(
                                        AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                }

                            }
                        });
                }
            });
            initSwipe();
        
    }
    
    
    
    public List<Data> fill_with_data() {

        List<Data> data = new ArrayList<>();

       // data.add(new Data("Jam Pelajaran","1"));
        //data.add(new Data("07:00 - 07:45", "10"));

        return data;
    }
    
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String key= data.get(position).sNamaBarang;
                //if (direction == ItemTouchHelper.RIGHT){
                    rvadapter.removeItem(position);
                    PrefUtil.removeString(MainActivity.this,key);
                    PrefUtil.removeStringCheck(MainActivity.this,key+"cb");
                //} else {
                   // rvadapter.removeItem(position);
                //}
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#ffcccccc"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hapus);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#ffcccccc"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_hapus);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.clear:
                rvadapter.removeAll();
                clear();
                return true;
            case R.id.exit:
                finish();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    

    public void clear()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        
        Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        SharedPreferences prefs2 = MainActivity.this.getSharedPreferences("status", Context.MODE_PRIVATE);
        Editor editor2 = prefs2.edit();
        editor2.clear();
        editor2.commit();
    }
}
