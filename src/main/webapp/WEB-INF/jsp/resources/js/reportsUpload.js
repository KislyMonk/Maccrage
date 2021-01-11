window.onload = function() {
var uploadsReports = new Vue({
    el: '#uploadReports',
  data: {
    files: []
  },
  methods: {
  	upload: function(){
      console.log("upload function");

      for(var i =0; i < this.$refs.uploadinput.files.length; i++){
        var data = new FormData();

          data.append("file", this.$refs.uploadinput.files[i]);

          axios.post('/Macragge/uploadReport',
               data
            ).then(function(response){
                
                for(var j =0; j < response.data.data.length; j++){
                    uploadsReports.files.push(response.data.data[j]);
                    console.log(response.data.data[j]);
                }
            }).catch(function(){
                console.log("0");
            });
        
      }

      
    }
  }
});
};

