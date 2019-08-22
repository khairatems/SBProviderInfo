/**
 * 
 */
package com.ing.tp.controller;

/**
 * Exception to handler unsupported/invalid provider.
 * 
 * @author 278684
 *
 */
public class UnsupportedProviderException extends Exception {

	private static final long serialVersionUID = 2670823733264743260L;

	public UnsupportedProviderException(final String message) {
		super(message);
	}

}
