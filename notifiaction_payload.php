
<?php
        require"connection.php";


        $title="Something";
        $message="New for you";


        define('API_ACCESS_KEY','YOUR_API_KEY');
        $msg=array
        (
        'body'=>$message,
        'title'=>$title,


        );

        $fields=array
        (
        'to'=>"/topics/alert",
        'priority'=>"high",
        'data'=>array("title"=>$title,"body"=>$message,"image"=>"IMG_URL")


        );

        $headers=array
        (
        'Authorization: key='.API_ACCESS_KEY,
        'Content-Type: application/json'
        );

        $ch=curl_init();
        curl_setopt($ch,CURLOPT_URL,'https://fcm.googleapis.com/fcm/send');
        curl_setopt($ch,CURLOPT_POST,true);
        curl_setopt($ch,CURLOPT_HTTPHEADER,$headers);
        curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
        curl_setopt($ch,CURLOPT_SSL_VERIFYPEER,false);
        curl_setopt($ch,CURLOPT_POSTFIELDS,json_encode($fields));
        $result=curl_exec($ch);
        curl_close($ch);
        echo $result;


        ?>   
