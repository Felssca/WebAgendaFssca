function atualiza_Pai() {
    window.opener.document.location.reload();
    window.close();
}
function mensagem(msg){
    
    alert(msg);
}
function decisao(msg){
    var name=confirm(msg)
    if (name==true)
    {
        return true;
    }
    else
    {
        return false;
    }


}
function ano(){
    dataAtual = new Date();
    return dataAtualo.getYear();
}



