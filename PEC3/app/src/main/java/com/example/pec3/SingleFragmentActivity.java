package com.example.pec3;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//esta clase
public abstract class SingleFragmentActivity extends AppCompatActivity {
	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.single_fragment);

		//si el fragmento es null
		if(fragment == null){
			//almacena en fragment el fragmento que devuelve crateFragment y lo añade al framelayout
			fragment = createFragment();
			fragmentManager.beginTransaction()
					.add(R.id.single_fragment,fragment)
					.commit();
		}
	}
}
