package kr.co.unitalent.util.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class S3 {

    private AwsKeyData awsKeyData = AwsKeyData.S3;
    private AWSCredentials credentials = new BasicAWSCredentials(awsKeyData.getAccessKey(), awsKeyData.getSecretKey());

    @Getter
    private AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();
}
