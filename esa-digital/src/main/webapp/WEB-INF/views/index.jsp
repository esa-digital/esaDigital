<!DOCTYPE html>
<html>

<head>
    <style>
    * {
        margin: 0;
    }
    
    embed {
        height: 100%;
    }
    
    .output {
        overflow: auto;
        height: 800px;
        position: relative;
    }
    
    .group {
        width: 50%;
        text-align: right;
    }
    </style>
</head>

<body>
    
    <p>endpoint:
        <input type="text" id="url" name="url" value="http://localhost:8084/api/pdfGenerator/generatePdf">
    </p>
    <div class="group">
        <p>firstName:
            <input type="text" id="firstName" name="firstName" value="Abilash" />
        </p>
        <p>surname:
            <input type="text" id="surname" name="surname" value="Madhavan" />
        </p>
        <p>dob:
            <input type="text" id="dob" name="dob" value="2016-05-27T10:59:10.050Z" />
        </p>
        <p>nino:
            <input type="text" id="nino" name="nino" value="SS112233A" />
        </p>
        <p>buildingNumber:
            <input type="text" id="buildingNumber" name="buildingNumber" value="100" />
        </p>
        <p>street:
            <input type="text" id="street" name="street" value="Warton Terrace" />
        </p>
        <p>town:
            <input type="text" id="town" name="town" value="Newcastle" />
        </p>
        <p>country:
            <input type="text" id="county" name="county" value="Tyne & Wear" />
        </p>
        <p>postCode:
            <input type="text" id="postCode:" name="postCode" value="NE6 5LS" />
        </p>
    </div>
    <button>send to endpoint</button>
    
    <div class="output"></div>

    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script>
    (function() {
        $('button').on('click', function() {

            $.ajax({
            		contentType: "application/json; charset=UTF-8",
            		dataType: "json",
                    method: "POST",
                    url: $('#url').val(),
                    data: JSON.stringify({
                        "address": {
                            "buildingNumber": $('#buildingNumber').val(),
                            "county": $('#county').val(),
                            "postCode": $('#postCode').val(),
                            "street": $('#street').val(),
                            "town": $('#town').val()
                        },
                        "dob": $('#dob').val(),
                        "firstName": $('#firstName').val(),
                        "nino": $('#nino').val(),
                        "surname": $('#surname').val()
                    })
                })
                .complete(function(msg) {
                	console.log(msg)
                	var htmlText = '<embed width=100% height=100%' + 
      			  ' type="application/pdf"' + 
      			  ' src="data:application/pdf;base64,' + 
    			  msg.responseText + 
      			  '"></embed>';
      $('.output').append(htmlText);
                });
        })
    })();
    </script>
</body>

</html>
