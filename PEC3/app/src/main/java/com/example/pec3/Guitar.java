package com.example.pec3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.UUID;

public class Guitar {

	//propiedades
	private UUID mUuid;
	private String mName;
	private byte[] mImage;
	private int mRating;

	//constructores
	public Guitar(){
		this(UUID.randomUUID());
	}

	public Guitar(String name, byte[] img){
		this(UUID.randomUUID());
		mName = name;
		mImage = img;
		mRating = 0;
	}

	public Guitar(UUID uuid){
		mUuid = uuid;
	}


	//getters y setters
	public UUID getmUuid() {
		return mUuid;
	}

	public void setmUuid(UUID mUuid) {
		this.mUuid = mUuid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public byte[] getmImage() {
		return mImage;
	}

	public void setmImage(byte[] mImage) {
		this.mImage = mImage;
	}

	public int getmRating() {
		return mRating;
	}

	public void setmRating(int mRating) {
		this.mRating = mRating;
	}
}
