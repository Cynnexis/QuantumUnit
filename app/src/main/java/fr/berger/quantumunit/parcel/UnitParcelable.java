package fr.berger.quantumunit.parcel;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.io.ObjectInputStream;
import java.io.Serializable;

import fr.berger.qube.Convertible;
import fr.berger.qube.Unit;
import fr.berger.qube.UnitType;

public class UnitParcelable extends Unit implements Serializable, Cloneable, Parcelable {
	
	public UnitParcelable(@NotNull String name, @NotNull String symbol, @NotNull UnitType type, @NotNull Convertible converter) {
		super(name, symbol, type, converter);
	}
	public UnitParcelable(@NotNull String name, char symbol, @NotNull UnitType type, @NotNull Convertible converter) {
		super(name, Character.toString(symbol), type, converter);
	}
	public UnitParcelable(char name, String symbol, @NotNull UnitType type, @NotNull Convertible converter) {
		super(Character.toString(name), symbol, type, converter);
	}
	public UnitParcelable(char name, char symbol, @NotNull UnitType type, @NotNull Convertible converter) {
		super(Character.toString(name), Character.toString(symbol), type, converter);
	}
	public UnitParcelable() {
		super();
	}
	
	@SuppressWarnings("ConstantConditions")
	public UnitParcelable(@NotNull Unit unit) {
		super();
		
		if (unit == null)
			throw new NullPointerException();
		
		setName(unit.getName());
		setSymbol(unit.getSymbol());
		setType(unit.getType());
		setConverter(unit.getConverter());
	}
	@SuppressWarnings("ConstantConditions")
	protected UnitParcelable(@NotNull Parcel in) {
		super();
		
		if (in == null)
			throw new NullPointerException();
		
		setName(in.readString());
		setSymbol(in.readString());
		setType(UnitType.valueOf(in.readString()));
		setConverter((Convertible) in.readValue(Convertible.class.getClassLoader()));
	}
	
	public static final Creator<UnitParcelable> CREATOR = new Creator<UnitParcelable>() {
		@Override
		public UnitParcelable createFromParcel(Parcel in) {
			return new UnitParcelable(in);
		}
		
		@Override
		public UnitParcelable[] newArray(int size) {
			return new UnitParcelable[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getName());
		dest.writeString(getSymbol());
		dest.writeString(getType().name());
		dest.writeValue(getConverter());
	}
}
