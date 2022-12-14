$('#submit').click(function() {
    // 创建shop对象
    var shop = {};
    if (isEdit) {
        // 若属于编辑，则给shopId赋值
        shop.shopId = shopId;
    }
    // 获取表单里的数据并填充进对应的店铺属性中
    shop.shopName = $('#shop-name').val();
    shop.shopAddr = $('#shop-addr').val();
    shop.phone = $('#shop-phone').val();
    shop.shopDesc = $('#shop-desc').val();
    // 选择选定好的店铺类别
    shop.shopCategory = {
        shopCategoryId : $('#shop-category').find('option').not(function() {
            return !this.selected;
        }).data('id')
    };
    // 选择选定好的区域信息
    shop.area = {
        areaId : $('#area').find('option').not(function() {
            return !this.selected;
        }).data('id')
    };
    // 获取上传的图片文件流
    var shopImg = $('#shop-img')[0].files[0];
    // 生成表单对象，用于接收参数并传递给后台
    var formData = new FormData();
    // 添加图片流进表单对象里
    formData.append('shopImg', shopImg);
    // 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
    formData.append('shopStr', JSON.stringify(shop));
    // 获取表单里输入的验证码
    var verifyCodeActual = $('#j_captcha').val();
    if (!verifyCodeActual) {
        $.toast('请输入验证码！');
        return;
    }
    formData.append('verifyCodeActual', verifyCodeActual);
    // 将数据提交至后台处理相关操作
    $.ajax({
        url : (isEdit ? editShopUrl : registerShopUrl),
        type : 'POST',
        data : formData,
        contentType : false,
        processData : false,
        cache : false,
        success : function(data) {
            if (data.success) {
                $.toast('提交成功！');
                if (!isEdit) {
                    // 若为注册操作，成功后返回店铺列表页
                    window.location.href = "/o2o/shopadmin/shoplist";
                }
            } else {
                $.toast('提交失败！' + data.errMsg);
            }
            // 点击验证码图片的时候，注册码会改变
            $('#captcha_img').click();
        }
    });
});