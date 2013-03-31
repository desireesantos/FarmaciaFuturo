package br.com.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class AtivarJob implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Lendo porta serial: " + new Date());
		
	}

}
