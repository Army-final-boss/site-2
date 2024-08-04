const audio = document.getElementById('audio');
const playPauseButton = document.getElementById('play-pause-button');
const volumeButton = document.getElementById('volume-button');
const volumeSlider = document.getElementById('volume-slider');
const progressBar = document.getElementById('progress-bar');
const currentTimeEl = document.getElementById('current-time');
const durationEl = document.getElementById('duration');

window.addEventListener('load', () => {
    audio.play();
    playPauseButton.innerHTML = '<i class="fas fa-pause"></i>';
});

playPauseButton.addEventListener('click', () => {
    if (audio.paused) {
        audio.play();
        playPauseButton.innerHTML = '<i class="fas fa-pause"></i>';
    } else {
        audio.pause();
        playPauseButton.innerHTML = '<i class="fas fa-play"></i>';
    }
});

audio.addEventListener('timeupdate', () => {
    progressBar.value = (audio.currentTime / audio.duration) * 100;
    currentTimeEl.textContent = formatTime(audio.currentTime);
    durationEl.textContent = formatTime(audio.duration);
});

progressBar.addEventListener('input', () => {
    audio.currentTime = (progressBar.value / 100) * audio.duration;
});

volumeSlider.addEventListener('input', () => {
    audio.volume = volumeSlider.value;
});

volumeButton.addEventListener('click', () => {
    if (audio.volume > 0) {
        audio.volume = 0;
        volumeSlider.value = 0;
        volumeButton.innerHTML = '<i class="fas fa-volume-mute"></i>';
    } else {
        audio.volume = 1;
        volumeSlider.value = 1;
        volumeButton.innerHTML = '<i class="fas fa-volume-up"></i>';
    }
});

function formatTime(seconds) {
    const minutes = Math.floor(seconds / 60);
    seconds = Math.floor(seconds % 60);
    if (seconds < 10) seconds = '0' + seconds;
    return `${minutes}:${seconds}`;
}
