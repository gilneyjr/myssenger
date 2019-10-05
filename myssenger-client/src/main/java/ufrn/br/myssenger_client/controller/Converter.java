package ufrn.br.myssenger_client.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import ufrn.br.myssenger_client.model.exception.ConvertFromBytesException;
import ufrn.br.myssenger_client.model.exception.ConvertToBytesException;

public class Converter {
	public static byte[] convertToBytes(Object object) throws ConvertToBytesException {
	    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
	         ObjectOutput out = new ObjectOutputStream(bos)) {
	        out.writeObject(object);
	        return bos.toByteArray();
	    } catch (IOException e) {
			throw new ConvertToBytesException(e);
		}
	}
	
	public static Object convertFromBytes(byte[] bytes) throws ConvertFromBytesException  {
	    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	         ObjectInput in = new ObjectInputStream(bis)) {
	        return in.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	    	throw new ConvertFromBytesException(e);
	    }
	}
}
