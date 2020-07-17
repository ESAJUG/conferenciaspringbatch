package org.jug.demo.batchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableBatchProcessing
public class BatchDemoApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BatchDemoApplication.class, args);
		JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");

		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		Job job = (Job)ctx.getBean("job");
		try {
			jobLauncher.run(job, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
