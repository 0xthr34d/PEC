package com.example.pec3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuitarListFragment extends Fragment {

	//variables miembro
	private RecyclerView mRecycleView;
	private GuitarAdapter mAdapter;
	private Toolbar mToolbar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


	//clase que contiene las vistas de cada elemento en la lista
	private class GuitarHolder extends RecyclerView.ViewHolder{

		//variables miembro
		private final ImageView mPhotoImageView;
		private ImageView mIconImageView;
		private TextView mNameTextView;
		private TextView mRatingTextView;
		private Guitar mGuitar;

		//constructor
		public GuitarHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_guitar, parent,false));
			//captura los View
			mPhotoImageView = (ImageView) itemView.findViewById(R.id.photo_imageview);
			mIconImageView = (ImageView) itemView.findViewById(R.id.icon_name_imageview);
			mIconImageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String newRatingString = Integer.toString(
							Integer.parseInt(
									mRatingTextView.getText().toString()) + 1
					);
					mRatingTextView.setText(newRatingString);
					GuitarLab.get(getActivity()).updateRating(Integer.parseInt(newRatingString), mGuitar.getmName());
				}
			});
			mNameTextView = (TextView) itemView.findViewById(R.id.name_textview);
			mRatingTextView = (TextView) itemView.findViewById(R.id.rating_textview);
		}

		public void bind(Guitar guitar){
			mGuitar = guitar;
			//setea los View
			mPhotoImageView.setImageBitmap(GuitarLab.transformImageToBitmap(mGuitar.getmImage()));
			mNameTextView.setText(mGuitar.getmName());
			mRatingTextView.setText(Integer.toString(mGuitar.getmRating()));
		}
	}

	//clase que vincula los datos con las vistas
	private class GuitarAdapter extends RecyclerView.Adapter<GuitarHolder>{
		//variables miembro
		private List<Guitar> mGuitars;

		//constructor
		public GuitarAdapter(List<Guitar> guitars){
			mGuitars = guitars;
		}

		@NonNull
		@Override
		public GuitarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
			return new GuitarHolder(layoutinflater,parent);
		}

		@Override
		public void onBindViewHolder(@NonNull GuitarHolder holder, int position) {
			//obtiene el objeto
			Guitar guitar = mGuitars.get(position);
			//vincula los datos con la vista llamando al metodo bind del la clase GuitarHolder
			holder.bind(guitar);
		}

		@Override
		public int getItemCount() {
			return mGuitars.size();
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guitar_list,container,false);
		//capturo la toolbar
		mToolbar = (Toolbar) view.findViewById(R.id.toolbar_list_guitar);
		//inserto el menú en la toolbar
		mToolbar.inflateMenu(R.menu.fragment_guitar_list);
		//seteo un listener a los items del menú
		mToolbar.setOnMenuItemClickListener(item -> {
			if(item.getItemId() == R.id.rating_star_toolbar){
				Intent intent = new Intent(getActivity(), GuitarTopListActivity.class);
				startActivity(intent);
			}
			return true;
		});
		//capturo el RecyclerView
		mRecycleView = (RecyclerView) view.findViewById(R.id.recycle_view_list);
		//setea un layout para el contenido
		mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
		//obtiene una lista de objetos Guitar
		List<Guitar> guitars = GuitarLab.get(getActivity()).getGuitars();
		//creo el adaptador
		mAdapter = new GuitarAdapter(guitars);
		//setea el adaptador
		mRecycleView.setAdapter(mAdapter);

		return view;
	}
}
