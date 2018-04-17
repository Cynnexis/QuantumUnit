package fr.berger.qube;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

import fr.berger.beyondcode.util.EnhancedObservable;
import fr.berger.qube.exceptions.ScaleNotValidException;

// https://en.wikipedia.org/wiki/Metric_prefix
public class Scale extends EnhancedObservable implements Serializable, Cloneable {

	private double value;

	/* CONSTRUCTORS */

	public Scale(double value) {
		setValue(value);
	}
	public Scale() {
		this(0);
	}

	/* SCALE METHOD */

	public static boolean isPowerOfTen(double value) {
		if (value == 0)
			return false;
		if (value == 1)
			return true;
		else if (value > 1) {
			while (value > 9 && value % 10 == 0)
				value /= 10;

			return value == 1;
		}
		else /*if (value < 1)*/ {
			// TODO:Trop imprécis
			while (value < 1)
				value *= 10;

			return value == 1;
		}
	}

	/* GETTER & SETTER */

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
        // If value is not a power of 10, then refuse it
		if (value != 0 && !isPowerOfTen(value))
		    throw new ScaleNotValidException(value);

		this.value = value;
		snap(this.value);
	}

	/* SERIALIZATION METHODS */

	private void writeObject(@NotNull ObjectOutputStream stream) throws IOException {
		stream.writeDouble(getValue());
	}

	private void readObject(@NotNull ObjectInputStream stream) throws IOException {
		setValue(stream.readDouble());
	}

	/* OVERRIDES */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Scale)) return false;
		Scale scale = (Scale) o;
		return Double.compare(scale.getValue(), getValue()) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public String toString() {
		int x = (int) Math.log10(getValue());

		if (x <= -18)
			return "a";
		else if (x <= -15 && -17 >= x)
			return "f";
		else if (x <= -12 && -14 >= x)
			return "p";
		else if (x <= -9 && -11 >= x)
			return "n";
		else if (x <= -6 && -8 >= x)
			return "μ";
		else if (x <= -3 && -5 >= x)
			return "m";
		else if (x == -2)
			return "c";
		else if (x == -1)
			return "d";
		else if (x == 0)
			return "";
		else if (x == 1)
			return "da";
		else if (x == 2)
			return "h";
		else if (3 <= x && x <= 5)
			return "k";
		else if (6 <= x && x <= 8)
			return "M";
		else if (9 <= x && x <= 11)
			return "G";
		else if (12 <= x && x <= 14)
			return "T";
		else if (15 <= x && x <= 17)
			return "P";
		else if (18 <= x)
			return "E";
		else
			return "?";
	}
}
