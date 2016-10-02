(ns sh.roosta.gallery.resources)

(def items
  [
   {:id 1 :w 411 :h 664 :title "Baby" :src "baby.jpg" :category :painting :desc "Oil painting of some baby, attempting a diverse colorscheme"}
   {:id 2 :w 993 :h 715 :title "Baptism" :src "baptism.jpg" :category :painting :desc "Watercolor baptism scene"}
   {:id 3 :w 961 :h 343 :title "Cake" :src "cake.jpg" :category :design :desc "One of several design suggestions for a webpage header"}
   {:id 4 :w 466 :h 475 :title "Capucha" :src "capucha.jpg" :category :vector :desc "Inspired by anime ghost figures, early vector attempt"}
   {:id 5 :w 549 :h 908 :title "Charcoal Nomad" :src "charnom.jpg" :category :design :desc "Charcoal masked nomad figure"}
   {:id 6 :w 667 :h 975 :title "Cloak in Motion" :src "cloak.jpg" :category :photo :desc "One is a series of dark room photographs experimenting with various light exposure settings during development"}
   {:id 7 :w 640 :h 82 :gif true :title "Conch" :src "conch.gif" :category :game :desc "Enemy design for an unfinished untitled shmup game"}
   {:id 8 :w 819 :h 1386 :title "Post cover" :src "cover.jpg" :category :painting :desc "Conseptual cover for a comic book that was never finished"}
   {:id 9 :w 437 :h 736 :title "Kanal Session Design" :src "cs.jpg" :category :design :desc "One of several design suggestions for a skating event. Not used but I still like it"}
   {:id 10 :w 1164 :h 785 :title "Drainpipes" :src "drainpipes.jpg" :category :photo :desc "Dark room development"}
   {:id 11 :w 1017 :h 723  :title "Dunes" :src "dunes.jpg" :category :painting :desc "Sand dunes abstracted"}
   {:id 12 :w 376 :h 520 :title "Masked Pencil Sketch" :src "edn.jpg" :category :drawing :desc "More pencil drawings of a gass mask wearing figure"}
   {:id 13 :w 121 :h 104 :gif true :title "Pixel Explosion" :src "expl.gif" :category :game :desc "Explosion from an unfinished shmup game"}
   {:id 14 :w 184 :h 358 :title "Floating Island" :src "flisland.png" :category :game :desc "Floating island for an unfinished shmup game"}
   {:id 15 :w 1193 :h 929 :title "Development fluid experiment" :src "fluid.jpg" :category :photo :desc "Messing around with development fluid that turned out kinda neat"}
   {:id 16 :w 711 :h 711  :title "Goat Boys LP cover design" :src "gbcover.jpg" :category :design :desc "Cover design for Goat Boys debut album" :link "https://open.spotify.com/artist/2GadG4cFR4bNH6fyLFt8JK"}
   {:id 17 :w 497 :h 635 :title "Ghost Guy" :src "ghost.jpg" :category :vector :desc "Early vector attempt at a ghost like creature inspired by anime/games and the masks ghosts wear"}
   {:id 18 :w 548 :h 320 :title "Gray Brother" :src "graybrother.jpg" :category :drawing :desc "Pencil drawing of a gass masked wearing person"}
   {:id 19 :w 1024 :h 576 :title "Hooded Moon" :src "hoodie.jpg" :category :design :desc "Cover suggestion for a band, that was never used"}
   {:id 20 :w 720 :h 470 :title "Hufda" :src "hufda.jpg" :category :design :desc "Cover suggestion for a band, it that was never used"}
   {:id 21 :w 779 :h 1087  :title "Selv Portrait with background" :src "inkdanfull.jpg" :category :painting :desc "Selv portrait with a superimposed background"}
   {:id 22 :w 625 :h 859 :title "Selv Portrait" :src "inkdans.jpg" :category :painting :desc "Selv portrait using ink on plastic"}
   {:id 23 :w 486 :h 566 :title "Insectlike" :src "insectlike.jpg" :category :drawing :desc "One of several inksect inspired mask drawings"}
   {:id 24 :w 609 :h 924 :title "Julie series nr 1" :src "js1.jpg" :category :photo :desc "First in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 26 :w 592 :h 869 :title "Julie series nr 3" :src "js3.jpg" :category :photo :desc "Third in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 27 :w 543 :h 827 :title "Julie series nr 4" :src "js4.jpg" :category :photo :desc "Forth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 28 :w 574 :h 854 :title "Julie series nr 5" :src "js5.jpg" :category :photo :desc "Fifth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 29 :w 865 :h 571 :title "Julie series nr 6" :src "js6.jpg" :category :photo :desc "Sixth in a photo series taken during medieval festival and developed in a darkroom"}
   {:id 30 :w 952 :h 1332 :title "Kodak Ice" :src "kodak.jpg" :category :photo :desc "Failed to develop this macro image of some ice but the result I still like"}
   {:id 31 :w 549 :h 731 :title "Desert Lady" :src "lady.jpg" :category :painting :desc "Unfinished painting trying to depict a desert-like dress style"}
   {:id 32 :w 489 :h 741 :title "Candle lamp" :src "lamp.jpg" :category :diy :desc "Lamp made for christmas using laser cutter, paint and patience. See link for vector blueprint" :link "https://roosta.sh/gallery/img/lamp_vector.svg"}
   {:id 33 :w 480 :h 480 :title "Monoxieman Idle" :src "monoxieman_idle.gif" :category :game :desc "Idle animation character sprite for unfinished game"}
   {:id 34 :w 480 :h 480 :title "Monoxieman Jump" :src "monoxieman_jump.gif" :category :game :desc "Jump animation character sprite for unfinished game"}
   {:id 35 :w 480 :h 480 :title "Monoxieman Running" :src "monoxieman_running.gif" :category :game :desc "Running animation character sprite for unfinished game"}
   {:id 36 :w 592 :h 800 :title "More ink!" :src "moreink.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 37 :w 592 :h 800 :title "More mask!" :src "moremask.jpg" :category :drawing :desc "Random sketch drawn with a rough hand"}
   {:id 38 :w 624 :h 788 :title "Nomad Angle" :src "nomad-ang.jpg" :category :painting :desc "Titular nomad drawn from an angle. Comic book concept"}
   {:id 39 :w 638 :h 878 :title "Nomad Equipped" :src "nomad-eq.jpg" :category :drawing :desc "Nomad with equipment, comic book concept"}
   {:id 40 :w 592 :h 725 :title "Ouroboros" :src "ouroboros.jpg" :category :drawing :desc "Unfinished sketch of ouroboros"}
   {:id 41 :w 752 :h 696 :title "Pattern Composition" :src "pattern_comp.jpg" :category :drawing :desc "One of many patterns inspired loosely on the art nouveau style"}
   {:id 42 :w 542 :h 769 :title "Pray Pencil" :src "pray_pencil.jpg" :category :drawing :desc "Sketch of two praying figures"}
   {:id 43 :w 1545 :h 380 :title "House gardener logo suggestion" :src "precore.png" :category :vector :desc "Logo suggestion for a design/gardening company. Not used but I still like it"}
   {:id 44 :w 598 :h 877 :title "Runny ink portrait" :src "riportrait.jpg" :category :painting :desc "Self portrait with gratuitous use of ink"}
   {:id 45 :w 610 :h 873 :title "Salt" :src "salt.jpg" :category :painting :desc "Salt/sand dunes abstracted illustration"}
   {:id 46 :w 592 :h 800 :title "Sectered" :src "secterd.jpg" :category :painting :desc "Gass mask wearing kid in the series of insect inspired gass masks"}
   {:id 47 :w 851 :h 315 :title "Shmup Screenshot" :src "shmup.png" :category :game :desc "Composit unfinished shmup screenshot from an unfinished untitled game"}
   {:id 48 :w 1019 :h 723 :title "Sea of sand" :src "sos.jpg" :category :painting :desc "Mountain surrounded by sand. Looks in hindsight more like water"}
   {:id 49 :w 850 :h 1163 :title "Splotch" :src "splotch.jpg" :category :painting :desc "Watercolor and ink drawing of a gassmask wearing creature"}
   {:id 50 :w 779 :h 566 :title "Stones study" :src "stones.jpg" :category :painting :desc "Study of stone texture, watercolor"}
   {:id 51 :w 872 :h 461 :title "Thirst" :src "thirst.jpg" :category :painting :desc "Thirsty fellow drawn with ink on rice paper"}
   {:id 52 :w 875 :h 593 :title "Three men" :src "tmen.jpg" :category :painting :desc "Card playing fellows drawn with ink on rice paper"}
   {:id 53 :w 600 :h 600 :title "VLN Cover suggestion" :src "vln.jpg" :category :design :desc "Pixel art cover suggestion for a band album"}
   {:id 54 :w 648 :h 628 :title "Wind Cover" :src "wind.jpg" :category :design :desc "Cover for a 60s inspired jam band. Marker on plastic"}
   {:id 55 :w 542 :h 762 :title "Wrap" :src "wrap.jpg" :category :drawing :desc "Cloth study, pencil"}
   ])
