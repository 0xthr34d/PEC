package com.example.pec3;

import androidx.fragment.app.Fragment;

public class GuitarTopListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new GuitarTopListFragment();
	}
}