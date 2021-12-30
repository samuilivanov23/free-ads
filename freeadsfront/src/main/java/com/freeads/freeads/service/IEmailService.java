package com.freeads.freeads.service;

public interface IEmailService
{
	boolean SendVerificationMail( String destinationEmailAddress, String recipientName, long recipientUserId );
	boolean VerifyEmailAddress( String token );
}
