package com.wjj.o2o.exceptions;

public class WechatAuthOperationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4815410077863863843L;

	public WechatAuthOperationException(String msg){
		super(msg);
	}
}
