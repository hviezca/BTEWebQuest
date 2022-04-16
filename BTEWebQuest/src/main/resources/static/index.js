// On load set Album completion percentage for indicator
window.onload = function onLoad() {
    let completionPercent = $('#albumPercent').val();
    let completionPercentConverted = completionPercent * 100;
    $('#albumPercentTracker').text(completionPercentConverted + '%');
    var progressBar =
        new ProgressBar.Circle('#progress', {
            color: 'rgba(245,6,45,0.48)',
            strokeWidth: 10,
            duration: 2000, // milliseconds
            easing: 'easeInOut'
        });

    progressBar.animate(completionPercent); // percent
};