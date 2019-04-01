package com.example.slider;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // LLamar todos los elementos que creamos en nuestra vista
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    private Button btnBack, btnNext;

    // Organizar la información que se va a mostrar en los Fragment por eso creamos arreglos
        private  String[] content = {"Hola 1", "Hola 2", "Hola 3", "Hola 4",};
        private String[] title = {"1","2","3","4"};
        private int [] image = {R.drawable.carroverde,R.drawable.carrodorado, R.drawable.carrorojo, R.drawable.carroverde};
        private  int [] colorBackground, colorDot;
        private TextView [] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mandamos a llamar los elementos que creamos en nuestra vista

        colorDot = getResources().getIntArray(R.array.array_dot);
        colorBackground = getResources().getIntArray(R.array.array_background);
        viewPager = findViewById(R.id.viewPager);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        dotsLayout = findViewById(R.id.layoutDots);
        addDots(0);
        loadViewPager();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next = getItem(+1);
                if (next<title.length){
                    viewPager.setCurrentItem(next);
                }else {
                    Toast.makeText(MainActivity.this, "Obtener promoción", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem()==title.length-1) {
                    finish();
                }else {
                    int back = getItem(-1);
                    viewPager.setCurrentItem(back);
                }
            }
        });
    }

    private int getItem (int i){

        return viewPager.getCurrentItem()+i;
    }

    // Método para recibir el Fragment actual
    private void addDots (int currentPage) {

        dots = new TextView[title.length];

        dotsLayout.removeAllViews();

        for (int i=0;i<title.length;i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (i==currentPage)
                dots[i].setTextColor(colorDot[currentPage]);
            else
                dots[i].setTextColor(Color.LTGRAY);

            dotsLayout.addView(dots[i]);
        }

    }

    // Agregamos los elementos en nuestro ViewPager y creamos una instancia para cada uno de nuestros Fragment
    private void loadViewPager (){
    adapter = new MyViewPagerAdapter(getSupportFragmentManager());
    for (int i=0;i<title.length;i++){
        adapter.addFragment(newInstance(title[i], content[i], image[i], colorBackground[i]));
    }

    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(pagerListener);

    }

    // Para llenar nuesto ViewPager con cada uno de los Fragment creamos otro método de tipo SliderFragment
    private  SliderFragment newInstance (String title, String content, int image, int color) {

        // Creamos una instancia diferente del Fragment, para colocar diferente información al interior
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        bundle.putInt("image", image);
        bundle.putInt("color", color);

        // Esta información la enviamos a nuetro Fragment creando una instancia
        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);

            if (position==title.length-1){
                btnNext.setText("Obtener");
                btnBack.setText("Salir");
            }else {
                btnNext.setText("Siguiente");
                btnBack.setText("Atrás");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
