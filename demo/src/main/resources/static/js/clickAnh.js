var check = 0;
var check2 = 0;

var img=[];
var nameIMG =[];

// Them loai san pham
const dialog = document.querySelector('#myDialog');
const openButton = document.querySelector('#openBT');
openButton.addEventListener('click', () => {
    dialog.showModal();
});
// close dialog
dialog.addEventListener('click', (event) => {
    if (event.target === dialog) {
        dialog.close();
    }
});


// Chon anh va hien anh
function displaySelectedImage() {
    console.log('htmmmm')
    console.log(checkA)
    var imgFile = document.querySelectorAll(".image2");
    var input = document.getElementById("myFileInput")
    var file = document.querySelector('input[type=file]').files[0];
    // var img = document.getElementsByClassName("2")[1];
    if (checkA.length != 0 && check2 < 3){
        console.log(img)
        check = checkA[check2];
    }
    console.log(check2 + " Day la check 2")

    console.log(check + " Day la check")
    if (check <= 2) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                if (check == 0){
                    img.push(document.getElementById('selectedImage'))
                }
                if (check == 1) {
                    img.push(document.getElementById('selectedImage2'))
                }
                if (check == 2) {
                    img.push(document.getElementById('selectedImage3'))
                }
                if (checkA.length == 0) {
                    for (let i = 0; i < img.length; i++) {
                        console.log(i + " la sao vay i")
                        if (check == i) {
                            nameIMG.push(file)
                            img[i].src = e.target.result;
                        }
                    }
                }
                else {
                    nameIMG.push(file)
                    imgFile[check].src = e.target.result;
                }

                check++;check2++;
            }
            // console.log(nameIMG)
            reader.readAsDataURL(input.files[0]);
        }
    }
}