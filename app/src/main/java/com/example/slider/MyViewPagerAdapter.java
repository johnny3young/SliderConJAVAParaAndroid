package com.example.slider;

// Con este Adapter manejaremos todos los fragment que creemos

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    List <Fragment> list = new ArrayList(); // Para manejar lista de Fragments

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i); // Obtener un elemento de la lista. Agregamos el index que seria el id que estariamos enviando para obtener un elemento de esta lista
    }

    @Override
    public int getCount() {
        return list.size(); // Nos sirve para saber cuántos elemenentos se encuentran en la lista
    }

    // Agregar elementos en nuestra lista
    public void addFragment (Fragment fragment) {
        list.add(fragment);
    }
}
